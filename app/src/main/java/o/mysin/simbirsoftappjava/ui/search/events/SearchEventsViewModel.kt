package o.mysin.simbirsoftappjava.ui.search.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.domain.model.Event
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import java.util.Locale

class SearchEventsViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _eventsList: MutableLiveData<List<Event>> = MutableLiveData()
    val eventsList: LiveData<List<Event>>
        get() = _eventsList

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun searchEvents(searchEvents: String) {
        val disposable = newsRepository.getObservableNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newsList ->
                val filterEvents = newsList.filter { item ->
                    searchEvents.lowercase(Locale.ROOT) in item.title.lowercase(Locale.ROOT)
                }.map { Event(it.title) }

                _eventsList.value = filterEvents
            }

        compositeDisposable.add(disposable)
    }

    fun cleanEventList() {
        viewModelScope.launch {
            _eventsList.value = emptyList()
        }
    }
}
