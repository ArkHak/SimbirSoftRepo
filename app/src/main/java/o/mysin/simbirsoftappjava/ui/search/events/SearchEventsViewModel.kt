package o.mysin.simbirsoftappjava.ui.search.events

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.domain.model.Event
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import java.util.Locale

class SearchEventsViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _eventsList: MutableLiveData<List<Event>> = MutableLiveData()
    val eventsList: LiveData<List<Event>>
        get() = _eventsList

    fun searchEvents(searchEvents: String) {
        viewModelScope.launch {
            newsRepository.getFlowableNews()
                .map { newsList ->
                    newsList.filter { item ->
                        searchEvents.lowercase(Locale.ROOT) in item.title.lowercase(Locale.ROOT)
                    }.map { Event(it.title) }
                }
                .catch { error ->
                    Log.e("MOD_TAG", "searchEvents: $error")
                    emit(emptyList())
                }
                .flowOn(Dispatchers.Main)
                .collect { filterEvents ->
                    _eventsList.value = filterEvents
                }
        }
    }

    fun cleanEventList() {
        viewModelScope.launch {
            _eventsList.value = emptyList()
        }
    }
}
