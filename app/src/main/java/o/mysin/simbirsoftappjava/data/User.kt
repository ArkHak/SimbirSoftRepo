package o.mysin.simbirsoftappjava.data

import o.mysin.simbirsoftappjava.R

data class User(
    val avatarSrc: Int? = R.drawable.temp_image_man,
    val name: String = "Олег Мысин",
    val birthday: String = "07 июля 1996г.",
    val work: String = "Android developer",
    val isPush: Boolean = false,
    val friendsList: List<Friend> = emptyList(),
)
