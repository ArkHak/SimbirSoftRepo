package ru.mys_ya.feature_news.ui.filter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.mys_ya.core.domain.repository.HelpCategoryRepository
import ru.mys_ya.core.domain.model.HelpCategory
import javax.inject.Inject

/** (1)
 * Учим даггер создавать FilterViewModel используя конструктор с параметрами
 */
class FilterViewModel @Inject constructor(
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val _filterList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val filterList: LiveData<List<HelpCategory>>
        get() = _filterList

    var tmpIdHelpCategoryHideList = arrayListOf<Int>()
        private set

    init {
        loadFilterList()
        /** (8)
         * В результате вызов этого лога должен происходить только во время чистого открытия экрана.
         * При перевороте экрана, лог вызываться не должен, так как создание вьюмодели в этот момент
         * происходить не должно
         */
        Log.e("FILTER", "viewmodel created")
    }

    private fun loadFilterList() {
        viewModelScope.launch {
            helpCategoryRepository.getHelpCategories()
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    Log.e("MOD_TAG", "loadHelpCategory: $error")
                }
                .collect { helpList ->
                    tmpIdHelpCategoryHideList.addAll(helpCategoryRepository.getIdHelpCategoriesHideList())
                    _filterList.value = helpList
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
            helpCategoryRepository.setIdHelpCategoriesHideList(tmpIdHelpCategoryHideList)
        }
    }
}
