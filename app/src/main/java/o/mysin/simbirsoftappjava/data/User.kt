package o.mysin.simbirsoftappjava.data

data class User(
    val avatarSrc: String = "null",
    val name: String = "Олег Мысин",
    val birthday: String = "07 июля 1996г.",
    val work: String = "Android developer",
    val isPush: Boolean = false,
    val friendsList: List<Friend>,
)