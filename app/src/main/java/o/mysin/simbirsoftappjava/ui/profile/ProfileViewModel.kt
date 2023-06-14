package o.mysin.simbirsoftappjava.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import o.mysin.simbirsoftappjava.data.Friend
import o.mysin.simbirsoftappjava.data.User

class ProfileViewModel : ViewModel() {

    private val _userProfile: MutableLiveData<User> = MutableLiveData()
    val userProfile: LiveData<User>
        get() = _userProfile

    init {
        val user = User(friendsList = loadFriends())
        _userProfile.value = user
    }

    fun loadFriends(): List<Friend> {
        return listOf(
            Friend(name = "Алексей Гладков"),
            Friend(name = "Филлип Киркоров"),
            Friend(name = "Наруто Узумаки"),
        )
    }
}
