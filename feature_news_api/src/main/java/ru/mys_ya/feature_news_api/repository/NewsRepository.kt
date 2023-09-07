package ru.mys_ya.feature_news_api.repository

import kotlinx.coroutines.flow.Flow
import ru.mys_ya.feature_news_api.domain.News

interface NewsRepository {
    fun getNews(): Flow<List<News>>
    fun getNews(id: Int): Flow<News>
    suspend fun putDatabase(listNews: List<News>)
}
