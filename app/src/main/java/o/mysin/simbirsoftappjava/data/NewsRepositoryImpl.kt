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
                    dateTime = 1687958180,
                    period = 1687958180,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.CHILDREN.ordinal
                    )
                ),
                News(
                    id = 2,
                    pictureUrl = R.drawable.tmp_two_item_news_picture,
                    title = "Конкурс по вокальному пению в детском доме №6",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    dateTime = 1687968980,
                    period = 1687968980,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.EVENTS.ordinal
                    )
                )
            )
        )
    }

    override fun getNews(): List<News> {
        return _listNews
    }

    override fun addNews(news: News) {
        _listNews.add(news)
    }
}
