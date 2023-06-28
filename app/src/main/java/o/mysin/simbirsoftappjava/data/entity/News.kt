package o.mysin.simbirsoftappjava.data.entity

data class News(
    val id: Int,
    val pictureUrl: Int,
    val title: String,
    val description: String,
    val dateTime: Long,
    val period: Long,
    val listHelpCategoryId: List<Int>,
)
