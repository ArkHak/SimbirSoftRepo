package o.mysin.simbirsoftappjava.ui.search.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SearchVewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val searchQuerySubject: BehaviorSubject<String> = BehaviorSubject.create()

    private val _queryByEventScreen: MutableLiveData<String> = MutableLiveData()
    val queryByEventScreen: LiveData<String>
        get() = _queryByEventScreen

    init {
        val disposable = searchQuerySubject
            .debounce(SEARCH_TIMEOUT, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query ->
                viewModelScope.launch {
                    _queryByEventScreen.value = query
                }
            }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun setSearchQuery(query: String) {
        searchQuerySubject.onNext(query)
    }

    companion object {
        private const val SEARCH_TIMEOUT = 500L
    }
}
