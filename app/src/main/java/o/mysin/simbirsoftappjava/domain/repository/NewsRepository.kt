package o.mysin.simbirsoftappjava.domain.repository

import o.mysin.simbirsoftappjava.domain.model.News

interface NewsRepository {
    fun getAllNews(): List<News>
    fun addListNews(listNews: List<News>)
    fun setIsViewedNews(idNews: Int)
}
