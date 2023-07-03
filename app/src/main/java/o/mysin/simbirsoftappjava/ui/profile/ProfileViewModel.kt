package o.mysin.simbirsoftappjava.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.domain.model.Friend
import o.mysin.simbirsoftappjava.domain.model.User

class ProfileViewModel : ViewModel() {

    private val _userProfile: MutableLiveData<User> = MutableLiveData()
    val userProfile: LiveData<User>
        get() = _userProfile

    init {
        val user = User()
        _userProfile.value = user
        loadFriends()
    }

    private fun loadFriends() {
        val list: List<Friend> = listOf(
            Friend(name = "Алексей Гладков", imageSrc = R.drawable.ava_gladkov),
            Friend(name = "Филлип Киркоров", imageSrc = R.drawable.ava_phill),
            Friend(name = "Наруто Узумаки", imageSrc = R.drawable.ava_naruto),
            Friend(name = "Алексей Гладков2", imageSrc = R.drawable.ava_gladkov),
            Friend(name = "Филлип Киркоров2", imageSrc = R.drawable.ava_phill),
            Friend(name = "Наруто Узумаки2", imageSrc = R.drawable.ava_naruto),
        )
        _userProfile.value = _userProfile.value?.copy(friendsList = list)
    }

    fun removeProfilePhoto() {
        _userProfile.value = _userProfile.value?.copy(avatarSrc = null)
    }
}
