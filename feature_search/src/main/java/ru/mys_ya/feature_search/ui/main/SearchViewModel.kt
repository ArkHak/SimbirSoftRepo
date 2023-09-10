package ru.mys_ya.feature_search.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val searchQueryFlow = MutableStateFlow("")

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
            searchQueryFlow.tryEmit(query)
    }

    companion object {
        private const val SEARCH_TIMEOUT = 500L
    }
}
