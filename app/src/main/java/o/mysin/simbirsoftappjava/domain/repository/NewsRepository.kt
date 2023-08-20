package o.mysin.simbirsoftappjava.domain.repository

import kotlinx.coroutines.flow.Flow
import o.mysin.simbirsoftappjava.domain.model.News

interface NewsRepository {
    fun getNews(): Flow<List<News>>
    fun getNews(id: Int): Flow<News>

}
