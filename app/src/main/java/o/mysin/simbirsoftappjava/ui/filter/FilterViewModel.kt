package o.mysin.simbirsoftappjava.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

class FilterViewModel(
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _filterList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val filterList: LiveData<List<HelpCategory>>
        get() = _filterList

    var tmpIdHelpCategoryHideList = arrayListOf<Int>()
        private set

    init {
        loadFilterList()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun loadFilterList() {
        val disposable = helpCategoryRepository.getHelpCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { helpList ->
                tmpIdHelpCategoryHideList.addAll(helpCategoryRepository.getIdHelpCategoriesHideList())
                _filterList.value = helpList
            }

        compositeDisposable.add(disposable)
    }

    fun changeIdHelpCategoryHideList(idtItem: Int) {
        if (tmpIdHelpCategoryHideList.contains(idtItem)) {
            tmpIdHelpCategoryHideList.remove(idtItem)
        } else {
            tmpIdHelpCategoryHideList.add(idtItem)
        }
    }

    fun saveFilterList() {
        viewModelScope.launch {
            helpCategoryRepository.setIdHelpCategoriesHideList(tmpIdHelpCategoryHideList)
        }
    }
}
