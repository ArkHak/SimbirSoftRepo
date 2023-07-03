package o.mysin.simbirsoftappjava.domain.repository

import o.mysin.simbirsoftappjava.domain.model.News

interface NewsRepository {
    fun getAllNews(): List<News>
    fun getNewsByFilter(filter: List<Int>): List<News>
    fun addNews(news: News)
    fun getNewsById(newsId: Int): News?
}
