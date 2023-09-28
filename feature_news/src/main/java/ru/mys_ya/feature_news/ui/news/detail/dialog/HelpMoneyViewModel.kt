package ru.mys_ya.feature_news.ui.news.detail.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HelpMoneyViewModel : ViewModel() {

    private val _isValid: MutableLiveData<Boolean> = MutableLiveData()
    val isValid: LiveData<Boolean>
        get() = _isValid

    private val _customMoneySum = MutableLiveData<String>()

    init {
        _customMoneySum.observeForever { _ ->
            checkIsValidValue()
        }
    }

    private fun checkIsValidValue() {
        if (!_customMoneySum.value.isNullOrEmpty()) run { ->
            this._isValid.value =
                _customMoneySum.value?.toInt() in MIN_CUSTOM_MONEY..MAX_CUSTOM_MONEY
        } else {
            this._isValid.value = false
        }
    }

    fun updateCustomMoneySum(sum: String) {
        viewModelScope.launch {
            _customMoneySum.value = sum
        }
    }

    fun clearMoneySum() {
        _customMoneySum.value = ""
    }

    companion object {
        private const val MIN_CUSTOM_MONEY = 1
        private const val MAX_CUSTOM_MONEY = 9_999_999
    }
}
