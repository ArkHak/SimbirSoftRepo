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

    private val _tmpFilterList = mutableListOf<HelpCategory>()

    init {
        loadFilterList()
        filterList.value?.toList()?.let { _tmpFilterList.addAll(it) }
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
                _filterList.value = helpList
            }

        compositeDisposable.add(disposable)
    }

    fun changeValueItemFilter(idtItem: Int) {
        val index = _tmpFilterList.indexOfFirst { it.id == idtItem }
        if (index != -1) {
            _tmpFilterList[index] =
                _tmpFilterList[index].copy(isEnabled = !_tmpFilterList[index].isEnabled)
        }
    }

    fun saveFilterList() {
        viewModelScope.launch {
            _filterList.value = _tmpFilterList
        }
    }
}
