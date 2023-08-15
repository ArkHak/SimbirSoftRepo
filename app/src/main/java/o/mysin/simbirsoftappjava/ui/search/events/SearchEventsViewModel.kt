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
import o.mysin.simbirsoftappjava.domain.model.SearchEvent
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import java.util.Locale

class SearchEventsViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {


    private val _eventsList: MutableLiveData<List<SearchEvent>> = MutableLiveData()
    val eventsList: LiveData<List<SearchEvent>>
        get() = _eventsList


    fun searchEvents(searchEvents: String) {

        viewModelScope.launch {
            newsRepository.getNews()
                .flowOn(Dispatchers.IO)
                .map { newsList ->
                    newsList.filter { item ->
                        searchEvents.lowercase(Locale.ROOT) in item.name.lowercase(Locale.ROOT)
                    }.map { SearchEvent(it.name) }
                }
                .catch { error ->
                    Log.e("MOD_TAG", "loadNews: $error")
                    emit(emptyList())
                }
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
