package o.mysin.simbirsoftappjava.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import java.util.concurrent.Executors

class HelpViewModel(
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    private val _helpCategoryList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val helpCategoryList: LiveData<List<HelpCategory>>
        get() = _helpCategoryList

    override fun onCleared() {
        super.onCleared()
        executor.shutdown()
    }

    fun updateData() {
        loadHelpCategoryFromExecutor()
    }

    private fun loadHelpCategoryFromCoroutines() {
        viewModelScope.launch {
            _helpCategoryList.value = helpCategoryRepository.getHelpCategories()
        }
    }

    private fun loadHelpCategoryFromExecutor() {
        executor.execute() {
            Thread.sleep(TIMEOUT)
            val list = helpCategoryRepository.getHelpCategories()
            _helpCategoryList.postValue(list)
        }
    }

    companion object {
        private const val TIMEOUT = 5000L
    }

}
