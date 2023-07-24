package o.mysin.simbirsoftappjava.ui.authorization

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentAuthorizationBinding
import o.mysin.simbirsoftappjava.ui.MainActivityViewModel

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private val binding: FragmentAuthorizationBinding by viewBinding()
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val authorizationViewModel: AuthorizationViewModel by viewModels()
    private var visibilityPassword = VisibilityPassword.OPEN.value
    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.setHideBottomNavigation(true)

        authorizationViewModel.isValid
            .observe(viewLifecycleOwner) { renderButtonLogin(it) }

        initActionButton()
        initPasswordField()
        initObserversTextFields()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initActionButton() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }
        binding.loginButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_authorization_fragment_to_fragment_help, null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.authorization_fragment, true)
                    .build()
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initPasswordField() {
        binding.passwordEditText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP && event.rawX >= (binding.passwordEditText.right - binding.passwordEditText.compoundDrawables[2].bounds.width())) {
                performIconClick()
                return@setOnTouchListener true
            }
            return@setOnTouchListener false
        }
    }

    private fun performIconClick() {
        visibilityPassword = !visibilityPassword
        val drawable = if (visibilityPassword) {
            ResourcesCompat.getDrawable(
                requireContext().resources,
                R.drawable.ic_open_password,
                null
            )
        } else {
            ResourcesCompat.getDrawable(
                requireContext().resources,
                R.drawable.ic_hide_password,
                null
            )
        }
        transformTextPassword()

        binding.passwordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            drawable,
            null
        )
    }

    private fun transformTextPassword() {
        if (visibilityPassword == VisibilityPassword.OPEN.value) {
            binding.passwordEditText.transformationMethod =
                PasswordTransformationMethod.getInstance()
        }
        if (visibilityPassword == VisibilityPassword.HIDE.value) {
            binding.passwordEditText.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        }
    }

    private fun initObserversTextFields() {
        val disposableEmailEdit = binding.emailEditText.textChanges()
            .skipInitialValue()
            .map(CharSequence::toString)
            .observeOn(Schedulers.io())
            .subscribe { text ->
                authorizationViewModel.updateEmailText(text)
            }

        val disposablePasswordEdit = binding.passwordEditText.textChanges()
            .skipInitialValue()
            .map(CharSequence::toString)
            .observeOn(Schedulers.io())
            .subscribe { text ->
                authorizationViewModel.updatePasswordText(text)
            }

        compositeDisposable.addAll(disposableEmailEdit, disposablePasswordEdit)
    }

    private fun renderButtonLogin(valid: Boolean) {
        binding.loginButton.isEnabled = valid
    }
}

private enum class VisibilityPassword(val value: Boolean) {
    OPEN(true),
    HIDE(false)
}
