package o.mysin.simbirsoftappjava.ui.search.main

import androidx.lifecycle.ViewModel

class SearchVewModel : ViewModel() {
    var queryByEventScreen = ""
        private set

    fun changeQueryByEventScreen(query: String) {
        queryByEventScreen = query
    }
}
