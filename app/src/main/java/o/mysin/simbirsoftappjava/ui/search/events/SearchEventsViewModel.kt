package o.mysin.simbirsoftappjava.ui.search.events

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.core.domain.model.SearchEvent
import ru.mys_ya.core.domain.usecase.GetSearchEventsByQueryUseCase
import ru.mys_ya.core.utils.ErrorMessage

class SearchEventsViewModel(
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
            try {
                _eventsList.value = searchEventsByQuery.invoke(searchQuery)
            } catch (error: Exception) {
                Log.e("MOD_TAG", "loadNews: $error")
                _errorMessage.value = ErrorMessage("loadNews: $error")
                _eventsList.value = emptyList()
            }
        }
    }

    fun cleanEventList() {
        viewModelScope.launch {
            _eventsList.value = emptyList()
        }
    }
}
