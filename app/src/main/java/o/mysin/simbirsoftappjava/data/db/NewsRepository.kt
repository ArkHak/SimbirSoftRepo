package o.mysin.simbirsoftappjava.data.db

import o.mysin.simbirsoftappjava.data.entity.News

interface NewsRepository {
    fun getAllNews(): List<News>
    fun getNewsByFilter(filter: List<Int>): List<News>
    fun addNews(news: News)
}
