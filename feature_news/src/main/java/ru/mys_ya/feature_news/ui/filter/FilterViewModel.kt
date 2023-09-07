package ru.mys_ya.feature_news.ui.filter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mys_ya.feature_help_api.model.HelpCategory
import ru.mys_ya.feature_help_api.usecase.GetHelpCategoriesUseCase
import ru.mys_ya.feature_help_api.usecase.GetIdHelpCategoriesHideUseCase
import ru.mys_ya.feature_help_api.usecase.SetIdHelpCategoriesHideUseCase
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val helpCategoriesUseCase: GetHelpCategoriesUseCase,
    private val getHelpCategoriesIdHideUseCase: GetIdHelpCategoriesHideUseCase,
    private val setCategoriesIdHideUseCase: SetIdHelpCategoriesHideUseCase,
) : ViewModel() {

    private val _filterList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val filterList: LiveData<List<HelpCategory>>
        get() = _filterList

    var tmpIdHelpCategoryHideList = arrayListOf<Int>()
        private set

    init {
        loadFilterList()
    }

    private fun loadFilterList() {
        viewModelScope.launch {
            try {
                _filterList.value = helpCategoriesUseCase.invoke()
                tmpIdHelpCategoryHideList.addAll(getHelpCategoriesIdHideUseCase())
            } catch (error: Exception) {
                Log.e("MOD_TAG", "loadHelpCategory: $error")
                _filterList.value = emptyList()
            }
        }
    }

    fun changeIdHelpCategoryHideList(idtItem: Int) {
        if (tmpIdHelpCategoryHideList.contains(idtItem)) {
            tmpIdHelpCategoryHideList.remove(idtItem)
        } else {
            tmpIdHelpCategoryHideList.add(idtItem)
        }
    }

    fun saveFilterList() {
        viewModelScope.launch(Dispatchers.IO) {
            setCategoriesIdHideUseCase(tmpIdHelpCategoryHideList)
        }
    }
}
