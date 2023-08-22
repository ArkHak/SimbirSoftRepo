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
import o.mysin.simbirsoftappjava.domain.usecase.GetSearchEventsByQueryUseCase
import o.mysin.simbirsoftappjava.utils.ErrorMessage

class SearchEventsViewModel(
    private val newsRepository: NewsRepository,
    private val searchEventsByQuery: GetSearchEventsByQueryUseCase,
) : ViewModel() {


    private val _eventsList: MutableLiveData<List<SearchEvent>> = MutableLiveData()
    val eventsList: LiveData<List<SearchEvent>>
        get() = _eventsList

    private val _errorMessage: MutableLiveData<ErrorMessage> = MutableLiveData()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    fun searchEvents(searchQuery: String) {

        viewModelScope.launch {
            newsRepository.getNews()
                .flowOn(Dispatchers.IO)
                .map { newsList ->
                    searchEventsByQuery.invoke(newsList, searchQuery)
                }
                .catch { error ->
                    Log.e("MOD_TAG", "loadNews: $error")
                    _errorMessage.value = ErrorMessage("loadNews: $error")
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
