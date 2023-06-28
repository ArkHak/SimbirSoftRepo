package o.mysin.simbirsoftappjava.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.data.db.HelpCategoryRepository
import o.mysin.simbirsoftappjava.data.entity.HelpCategory

class FilterViewModel(
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val _filterList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val filterList: LiveData<List<HelpCategory>>
        get() = _filterList

    init {
        loadFilterList()
    }

    private fun loadFilterList() {
        viewModelScope.launch {
            _filterList.value = helpCategoryRepository.getHelpCategory()
        }
    }

    fun changeValueItemFilter(idItem: Int) {
        helpCategoryRepository.changeEnabledItemById(idItem)
    }

}
