package o.mysin.simbirsoftappjava.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

class HelpViewModel(
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _helpCategoryList: MutableLiveData<List<HelpCategory>> = MutableLiveData()
    val helpCategoryList: LiveData<List<HelpCategory>>
        get() = _helpCategoryList

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun updateData() {
        if (_helpCategoryList.value.isNullOrEmpty()) {
            loadHelpCategoryFromRx()
        }
    }

    private fun loadHelpCategoryFromRx() {
        val disposableHelpList = Observable.fromCallable {
            Thread.sleep(TIMEOUT)
            helpCategoryRepository.getHelpCategories()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                _helpCategoryList.postValue(list)
            }
        compositeDisposable.add(disposableHelpList)
    }

    companion object {
        private const val TIMEOUT = 1000L
    }

}
