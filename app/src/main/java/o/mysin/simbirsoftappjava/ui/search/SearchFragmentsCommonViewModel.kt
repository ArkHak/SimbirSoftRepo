package o.mysin.simbirsoftappjava.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchFragmentsCommonViewModel : ViewModel() {

    private val _titleSearchView: MutableLiveData<Int> = MutableLiveData()
    val titleSearchView: LiveData<Int>
        get() = _titleSearchView

    private val _queryEvents: MutableLiveData<String> = MutableLiveData()
    val queryEvents: LiveData<String>
        get() = _queryEvents

    fun correctTitleSearchView(idTitle: Int) {
        viewModelScope.launch {
            _titleSearchView.value = idTitle
        }
    }

    fun sendQueryToSearchEvents(query: String) {
        viewModelScope.launch {
            _queryEvents.value = query
        }
    }
}
