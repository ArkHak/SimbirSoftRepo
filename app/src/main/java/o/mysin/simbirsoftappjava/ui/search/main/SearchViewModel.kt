package o.mysin.simbirsoftappjava.ui.search.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {

    private val searchQueryFlow = MutableSharedFlow<String>()

    private val _queryByEventScreen: MutableLiveData<String> = MutableLiveData()
    val queryByEventScreen: LiveData<String>
        get() = _queryByEventScreen

    init {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(SEARCH_TIMEOUT)
                .flowOn(Dispatchers.IO)
                .onEach { query ->
                    _queryByEventScreen.value = query
                }
                .catch { error ->
                    Log.e("MOD_TAG", "Ошибка при получении запроса: $error")
                }
                .collect()
        }
    }

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            searchQueryFlow.emit(query)
        }
    }

    companion object {
        private const val SEARCH_TIMEOUT = 500L
    }
}
