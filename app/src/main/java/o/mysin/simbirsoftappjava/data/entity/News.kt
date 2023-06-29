package o.mysin.simbirsoftappjava.data.entity

data class News(
    val id: Int,
    val pictureUrl: Int,
    val title: String,
    val description: String,
    val startDateTime: Long,
    val endDateTime: Long,
    val listHelpCategoryId: List<Int>,
)
