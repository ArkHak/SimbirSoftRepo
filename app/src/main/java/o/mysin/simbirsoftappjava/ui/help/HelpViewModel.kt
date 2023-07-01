package o.mysin.simbirsoftappjava.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.data.db.HelpCategoryRepository
import o.mysin.simbirsoftappjava.data.entity.HelpCategory

class HelpViewModel(
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val _helpCategoryList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val helpCategoryList: LiveData<List<HelpCategory>>
        get() = _helpCategoryList

    init {
        loadHelpCategory()
    }

    private fun loadHelpCategory() {
        viewModelScope.launch {
            _helpCategoryList.value = helpCategoryRepository.getHelpCategory()
        }
    }

}
