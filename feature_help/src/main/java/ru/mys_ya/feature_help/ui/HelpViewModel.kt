package ru.mys_ya.feature_help.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.feature_help_api.model.HelpCategory
import ru.mys_ya.core.utils.ErrorMessage
import ru.mys_ya.feature_help_api.usecase.GetHelpCategoriesUseCase
import javax.inject.Inject

class HelpViewModel @Inject constructor(
    private val helpCategoriesUseCase: GetHelpCategoriesUseCase,
) : ViewModel() {

    private val _helpCategoryList: MutableLiveData<List<HelpCategory>> =
        MutableLiveData()
    val helpCategoryList: LiveData<List<HelpCategory>>
        get() = _helpCategoryList

    private val _errorMessage: MutableLiveData<ErrorMessage> = MutableLiveData()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    fun updateData() {
            loadHelpCategory()
    }

    private fun loadHelpCategory() {
        viewModelScope.launch {
            try {
                val helpCategoriesList = helpCategoriesUseCase()
                _helpCategoryList.value = helpCategoriesList
            } catch (error: Exception) {
                Log.e("MOD_TAG", "loadHelpCategory: $error")
                _errorMessage.value = ErrorMessage("loadHelpCategory: $error")
                _helpCategoryList.value = emptyList()
            }
        }
    }
}
