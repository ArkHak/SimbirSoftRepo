package o.mysin.simbirsoftappjava.ui.search.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        val filterEvents = newsRepository.getAllNews().filter { item ->
            searchEvents.lowercase(Locale.ROOT) in item.title.lowercase(Locale.ROOT)
        }.map { Event(it.title) }

        viewModelScope.launch {
            _eventsList.value = filterEvents
        }
    }
}
