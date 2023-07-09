package o.mysin.simbirsoftappjava.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _hideBottomNavigation = MutableLiveData<Boolean>()
    val hideBottomNavigation: LiveData<Boolean>
        get() = _hideBottomNavigation

    fun setHideBottomNavigation(hide: Boolean) {
        _hideBottomNavigation.value = hide
    }
}
