package o.mysin.simbirsoftappjava.data.entity

import o.mysin.simbirsoftappjava.R

data class User(
    val avatarSrc: Int? = R.drawable.temp_image_man,
    val name: String = "Олег Мысин",
    val birthday: Long = 836683200,
    val work: String = "Android developer",
    val isPush: Boolean = false,
    val friendsList: List<Friend> = emptyList(),
)
