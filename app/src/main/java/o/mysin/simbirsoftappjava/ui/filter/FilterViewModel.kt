package o.mysin.simbirsoftappjava.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

class FilterViewModel(
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val _filterList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val filterList: LiveData<List<HelpCategory>>
        get() = _filterList

    private val _tmpFilterList = mutableListOf<HelpCategory>()

    init {
        loadFilterList()
        filterList.value?.toList()?.let { _tmpFilterList.addAll(it) }
    }


    private fun loadFilterList() {
        viewModelScope.launch {
            _filterList.value = helpCategoryRepository.getHelpCategories()
        }
    }

    fun changeValueItemFilter(idtItem: Int) {
        val index = _tmpFilterList.indexOfFirst { it.id == idtItem }
        if (index != -1) {
            _tmpFilterList[index] =
                _tmpFilterList[index].copy(isEnabled = !_tmpFilterList[index].isEnabled)
        }
    }

    fun saveFilterList() {
        helpCategoryRepository.updateList(_tmpFilterList)
    }
}
