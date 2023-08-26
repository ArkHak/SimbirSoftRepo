package ru.mys_ya.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mys_ya.core.domain.model.News

interface NewsRepository {
    fun getNews(): Flow<List<News>>
    fun getNews(id: Int): Flow<News>
    suspend fun putDatabase(listNews: List<News>)
}
