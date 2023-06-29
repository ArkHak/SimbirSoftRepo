package o.mysin.simbirsoftappjava.data

import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.db.NewsRepository
import o.mysin.simbirsoftappjava.data.entity.News

class NewsRepositoryImpl : NewsRepository {

    private val _listNews = mutableListOf<News>()

    init {
        _listNews.addAll(
            listOf(
                News(
                    id = 1,
                    pictureUrl = R.drawable.tmp_item_news_picture,
                    title = "Спонсоры отремонтируют школу-интернат",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    startDateTime = 1687958180,
                    endDateTime = 1690318800,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.CHILDREN.ordinal
                    )
                ),
                News(
                    id = 2,
                    pictureUrl = R.drawable.tmp_two_item_news_picture,
                    title = "Конкурс по вокальному пению в детском доме №6",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    startDateTime = 1687968980,
                    endDateTime = 1690491600,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.EVENTS.ordinal
                    )
                ),
                News(
                    id = 3,
                    pictureUrl = R.drawable.tmp_item_news_picture,
                    title = "Спонсоры отремонтируют школу-интернат",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    startDateTime = 1687958180,
                    endDateTime = 1687958180,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.CHILDREN.ordinal
                    )
                ),
                News(
                    id = 4,
                    pictureUrl = R.drawable.tmp_two_item_news_picture,
                    title = "Конкурс по вокальному пению в детском доме №6",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    startDateTime = 1687968980,
                    endDateTime = 1687968980,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.EVENTS.ordinal
                    )
                ),
                News(
                    id = 5,
                    pictureUrl = R.drawable.tmp_item_news_picture,
                    title = "Спонсоры отремонтируют школу-интернат",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    startDateTime = 1687958180,
                    endDateTime = 1690491600,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.CHILDREN.ordinal
                    )
                ),
            )
        )
    }

    override fun getAllNews(): List<News> {
        return _listNews
    }

    override fun getNewsByFilter(filter: List<Int>): List<News> {
        return _listNews
            .filter { it.listHelpCategoryId.intersect(filter.toSet()).isNotEmpty() }
    }

    override fun addNews(news: News) {
        _listNews.add(news)
    }
}
