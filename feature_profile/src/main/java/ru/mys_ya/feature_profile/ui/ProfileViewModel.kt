package ru.mys_ya.feature_profile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mys_ya.feature_profile_api.model.Friend
import ru.mys_ya.feature_profile_api.model.User

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
            Friend(
                name = "Алексей Гладков",
                imageSrc = "https://thumb.tildacdn.com/tild3739-3337-4530-b562-643539663265/-/format/webp/_.jpg"
            ),
            Friend(
                name = "Филлип Киркоров",
                imageSrc = "https://s1.bloknot-voronezh.ru/thumb/850x0xcut/upload/iblock/1ae/8r55oxgxldtr24o21gorz5bjvkqf1d74/Kirkorov.png"
            ),
            Friend(
                name = "Наруто Узумаки",
                imageSrc = "https://arthive.net/res/media/img/orig/work/58b/7525132.jpg"
            ),
            Friend(
                name = "Алексей Гладков2",
                imageSrc = "https://thumb.tildacdn.com/tild3739-3337-4530-b562-643539663265/-/format/webp/_.jpg"
            ),
            Friend(
                name = "Филлип Киркоров2",
                imageSrc = "https://s1.bloknot-voronezh.ru/thumb/850x0xcut/upload/iblock/1ae/8r55oxgxldtr24o21gorz5bjvkqf1d74/Kirkorov.png"
            ),
            Friend(
                name = "Наруто Узумаки2",
                imageSrc = "https://arthive.net/res/media/img/orig/work/58b/7525132.jpg"
            ),
        )
        _userProfile.value = _userProfile.value?.copy(friendsList = list)
    }

    fun removeProfilePhoto() {
        _userProfile.value = _userProfile.value?.copy(avatarSrc = null)
    }
}
