package o.mysin.simbirsoftappjava.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val _hideBottomNavigation = MutableLiveData<Boolean>()
    val hideBottomNavigation: LiveData<Boolean>
        get() = _hideBottomNavigation


    private val _badgeFlow = MutableStateFlow(0)
    val badgeFlow: StateFlow<Int> = _badgeFlow

    fun setBadgeCount(count: Int) {
        viewModelScope.launch {
            _badgeFlow.emit(count)
        }
    }

    fun setHideBottomNavigation(hide: Boolean) {
        _hideBottomNavigation.value = hide
    }
}
