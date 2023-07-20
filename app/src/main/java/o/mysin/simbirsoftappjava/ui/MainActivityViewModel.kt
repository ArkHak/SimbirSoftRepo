package o.mysin.simbirsoftappjava.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainActivityViewModel : ViewModel() {
    private val _hideBottomNavigation = MutableLiveData<Boolean>()
    val hideBottomNavigation: LiveData<Boolean>
        get() = _hideBottomNavigation

    val badgeSubject: BehaviorSubject<Int> = BehaviorSubject.createDefault(0)

    fun setBadgeCount(count: Int) {
        badgeSubject.onNext(count)
    }


    fun setHideBottomNavigation(hide: Boolean) {
        _hideBottomNavigation.value = hide
    }
}
