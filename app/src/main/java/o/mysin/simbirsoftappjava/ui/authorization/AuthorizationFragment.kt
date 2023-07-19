package o.mysin.simbirsoftappjava.ui.authorization

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentAuthorizationBinding
import o.mysin.simbirsoftappjava.ui.MainActivityViewModel

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private val binding: FragmentAuthorizationBinding by viewBinding()
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private var visibilityPassword = VisibilityPassword.OPEN.value

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.setHideBottomNavigation(true)

        initActionButton()
        initPasswordField()
        initObserversTextFields()
    }

    private fun initActionButton() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_fragment_to_fragment_help)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initPasswordField() {
        binding.passwordEditText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.passwordEditText.right - binding.passwordEditText.compoundDrawables[2].bounds.width())) {
                    performIconClick()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun performIconClick() {
        visibilityPassword = !visibilityPassword
        val drawable = if (visibilityPassword) {
            resources.getDrawable(R.drawable.ic_open_password, null)
        } else {
            resources.getDrawable(R.drawable.ic_hide_password, null)
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

    @SuppressLint("CheckResult")
    private fun initObserversTextFields() {
        val emailObserver = binding.emailEditText.textChanges().map {
            it.length >= SYMBOL_COUNT
        }
        val passwordObserver = binding.passwordEditText.textChanges().map {
            it.length >= SYMBOL_COUNT
        }

        Observable.combineLatest(
            emailObserver,
            passwordObserver
        ) { emailValid: Boolean, passwordValid: Boolean -> emailValid && passwordValid }
            .subscribe { isValid -> renderButtonLogin(isValid) }
    }

    private fun renderButtonLogin(valid: Boolean) {
        val color = if (valid) {
            requireContext().getColor(R.color.leaf_light)
        } else {
            requireContext().getColor(R.color.grey)
        }
        binding.loginButton.isEnabled = valid
        binding.loginButton.backgroundTintList = ColorStateList.valueOf(color)
    }

    companion object {
        private const val SYMBOL_COUNT = 6
    }

}

private enum class VisibilityPassword(val value: Boolean) {
    OPEN(true),
    HIDE(false)
}
