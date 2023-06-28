package o.mysin.simbirsoftappjava.data.db

import o.mysin.simbirsoftappjava.data.entity.News

interface NewsRepository {
    fun getNews(): List<News>
    fun addNews(news: News)
}
