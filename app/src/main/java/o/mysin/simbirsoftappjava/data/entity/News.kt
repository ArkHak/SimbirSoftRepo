package o.mysin.simbirsoftappjava.data.entity

data class News(
    val id: Int,
    val owner: String,
    val ownerAddress: String,
    val ownerContacts: String,
    val picturesUrl: List<Int>,
    val title: String,
    val description: String,
    val fullDescription: String,
    val startDateTime: Long,
    val endDateTime: Long,
    val listHelpCategoryId: List<Int>,
)
