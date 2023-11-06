package ru.mys_ya.feature_news.ui.news.detail.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.mys_ya.feature_news.R
import ru.mys_ya.feature_news.databinding.DialogFragmentHelpMoneyBinding
import ru.mys_ya.feature_news.ui.news.detail.NewsDetailFragment.Companion.REQUEST_SEND_SUM_MONEY
import ru.mys_ya.feature_news.ui.news.detail.NewsDetailFragment.Companion.SEND_SUM_MONEY

class HelpMoneyDialogFragment : DialogFragment(R.layout.dialog_fragment_help_money) {

    private val binding: DialogFragmentHelpMoneyBinding by viewBinding()
    private val helpMoneyViewModel: HelpMoneyViewModel by viewModels()

    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        helpMoneyViewModel.isValid
            .observe(viewLifecycleOwner) { renderButtonSend(it) }

        initActionButtons()
        initObserversTextField()

        binding.defaultMoneySum.addOnButtonCheckedListener { buttonGroup, checkedId, _ ->
            if (buttonGroup.checkedButtonId != NO_TOGGLE_BUTTONS_GROUP_SELECTED) {
                binding.moneySumEditText.text?.clear()
                when (checkedId) {
                    R.id.money_sum_100 ->
                        helpMoneyViewModel.updateCustomMoneySum(DefaultMoneySum.SUM100.moneySum)

                    R.id.money_sum_500 ->
                        helpMoneyViewModel.updateCustomMoneySum(DefaultMoneySum.SUM500.moneySum)

                    R.id.money_sum_1000 ->
                        helpMoneyViewModel.updateCustomMoneySum(DefaultMoneySum.SUM1000.moneySum)

                    R.id.money_sum_2000 ->
                        helpMoneyViewModel.updateCustomMoneySum(DefaultMoneySum.SUM2000.moneySum)
                }
            } else {
                helpMoneyViewModel.clearMoneySum()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
            dialog.window!!.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun renderButtonSend(valid: Boolean) {
        binding.buttonSend.isEnabled = valid
    }

    private fun initActionButtons() {
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonSend.setOnClickListener {
            val currentMoneySum = helpMoneyViewModel.getMoneySum()
            setFragmentResult(REQUEST_SEND_SUM_MONEY, bundleOf(SEND_SUM_MONEY to currentMoneySum))
            dismiss()
        }
    }

    private fun initObserversTextField() {
        val disposableMoneySum = binding.moneySumEditText.textChanges()
            .skipInitialValue()
            .map(CharSequence::toString)
            .observeOn(Schedulers.io())
            .subscribe { customSum ->
                helpMoneyViewModel.updateCustomMoneySum(customSum)
            }

        compositeDisposable.add(disposableMoneySum)
    }


    companion object {
        const val TAG = "HelpMoneyDialogFragment"
        const val NO_TOGGLE_BUTTONS_GROUP_SELECTED = -1
    }

    enum class DefaultMoneySum(val moneySum: String) {
        SUM100("100"),
        SUM500("500"),
        SUM1000("1000"),
        SUM2000("2000")
    }
}
