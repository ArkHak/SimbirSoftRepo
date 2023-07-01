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
                    owner = "Благотворительный Фонд «Счастливый Мир»",
                    ownerAddress = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                    ownerContacts = "+7 (937) 037 37-73\n+7 (937) 016 16-16",
                    picturesUrl = listOf(
                        R.drawable.tmp_news_picture,
                        R.drawable.tmp_news_picture_2,
                        R.drawable.tmp_news_picture_3,
                    ),
                    title = "Спонсоры отремонтируют школу-интернат",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    fullDescription = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
                    startDateTime = 1687958180,
                    endDateTime = 1690318800,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.CHILDREN.ordinal
                    )
                ),
                News(
                    id = 2,
                    owner = "Благотворительный Фонд «Счастливый Мир»",
                    ownerAddress = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                    ownerContacts = "+7 (937) 037 37-73\n+7 (937) 016 16-16",
                    picturesUrl = listOf(
                        R.drawable.tmp_two_news_picture,
                        R.drawable.tmp_news_picture_2,
                        R.drawable.tmp_news_picture_3,
                    ), title = "Конкурс по вокальному пению в детском доме №6",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    fullDescription = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
                    startDateTime = 1687968980,
                    endDateTime = 1690491600,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.EVENTS.ordinal
                    )
                ),
                News(
                    id = 3,
                    owner = "Благотворительный Фонд «Счастливый Мир»",
                    ownerAddress = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                    ownerContacts = "+7 (937) 037 37-73\n+7 (937) 016 16-16",
                    picturesUrl = listOf(
                        R.drawable.tmp_news_picture,
                        R.drawable.tmp_news_picture_2,
                        R.drawable.tmp_news_picture_3,
                    ), title = "Спонсоры отремонтируют школу-интернат",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    fullDescription = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
                    startDateTime = 1687958180,
                    endDateTime = 1687958180,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.CHILDREN.ordinal
                    )
                ),
                News(
                    id = 4,
                    owner = "Благотворительный Фонд «Счастливый Мир»",
                    ownerAddress = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                    ownerContacts = "+7 (937) 037 37-73\n+7 (937) 016 16-16",
                    picturesUrl = listOf(
                        R.drawable.tmp_two_news_picture,
                        R.drawable.tmp_news_picture_2,
                        R.drawable.tmp_news_picture_3,
                    ), title = "Конкурс по вокальному пению в детском доме №6",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    fullDescription = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
                    startDateTime = 1687968980,
                    endDateTime = 1687968980,
                    listHelpCategoryId = listOf(
                        HelpCategoryFixList.EVENTS.ordinal
                    )
                ),
                News(
                    id = 5,
                    owner = "Благотворительный Фонд «Счастливый Мир»",
                    ownerAddress = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                    ownerContacts = "+7 (937) 037 37-73\n+7 (937) 016 16-16",
                    picturesUrl = listOf(
                        R.drawable.tmp_news_picture,
                        R.drawable.tmp_news_picture_2,
                        R.drawable.tmp_news_picture_3,
                    ), title = "Спонсоры отремонтируют школу-интернат",
                    description = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    fullDescription = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
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

    override fun getNewsById(newsId: Int): News? {
        return _listNews.find { it.id == newsId }
    }
}
