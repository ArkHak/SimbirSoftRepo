package ru.mys_ya.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel : ViewModel() {
    private val _hideBottomNavigation = MutableLiveData<Boolean>()
    val hideBottomNavigation: LiveData<Boolean>
        get() = _hideBottomNavigation


    private val _badgeFlow = MutableStateFlow(0)
    val badgeFlow: StateFlow<Int> = _badgeFlow

    fun setBadgeCount(count: Int) {
        _badgeFlow.tryEmit(count)
    }

    fun setHideBottomNavigation(hide: Boolean) {
        _hideBottomNavigation.value = hide
    }
}
