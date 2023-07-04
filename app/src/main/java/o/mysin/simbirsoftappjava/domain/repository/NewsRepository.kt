package o.mysin.simbirsoftappjava.domain.repository

import o.mysin.simbirsoftappjava.domain.model.News

fun interface NewsRepository {
    fun getAllNews(): List<News>

}
