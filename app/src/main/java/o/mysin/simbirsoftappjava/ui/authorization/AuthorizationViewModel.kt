package o.mysin.simbirsoftappjava.ui.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class AuthorizationViewModel : ViewModel() {

    private val _isValid: MutableLiveData<Boolean> = MutableLiveData()
    val isValid: LiveData<Boolean>
        get() = _isValid

    private val _emailTextField = MutableLiveData<String>()
    private val _passwordTextField = MutableLiveData<String>()

    init {
        _emailTextField.observeForever { _ ->
            checkIsValidValue()
        }
        _passwordTextField.observeForever { _ ->
            checkIsValidValue()
        }
    }

    private fun checkIsValidValue() {
        val isEmailValid = _emailTextField.value?.let {
            it.length >= SYMBOL_COUNT
        } ?: false

        val isPasswordValid = _passwordTextField.value?.let {
            it.length >= SYMBOL_COUNT
        } ?: false

        _isValid.value = isEmailValid && isPasswordValid
    }


    fun updateEmailText(text: String) {
        viewModelScope.launch {
            _emailTextField.value = text
        }
    }

    fun updatePasswordText(text: String) {
        viewModelScope.launch {
            _passwordTextField.value = text
        }
    }

    companion object {
        private const val SYMBOL_COUNT = 6
    }
}
