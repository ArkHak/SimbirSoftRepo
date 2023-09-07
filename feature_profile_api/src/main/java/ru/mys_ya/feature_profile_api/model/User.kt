package ru.mys_ya.feature_profile_api.model

data class User(
    val avatarSrc: String? = "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/F3374F01-1829-407F-9955-EFD70C08794F.png",
    val name: String = "Олег Мысин",
    val birthday: Long = 836683200,
    val work: String = "Android developer",
    val isPush: Boolean = false,
    val friendsList: List<Friend> = emptyList(),
)
