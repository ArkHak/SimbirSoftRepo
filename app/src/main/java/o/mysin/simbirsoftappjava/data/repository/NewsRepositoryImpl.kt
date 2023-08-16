package o.mysin.simbirsoftappjava.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import o.mysin.simbirsoftappjava.data.db.dao.EventDao
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.domain.mapper.EventMapper
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class NewsRepositoryImpl(
    private val assetManager: AssetManager,
    private val apiService: ApiService,
    private val eventDao: EventDao,
    private val mapper: EventMapper,
) : NewsRepository {

    override fun getNews(): Flow<List<News>> = flow {
        eventDao.getAllEvents().map { event ->
            mapper.fromEvent(event)
        }.also { newsFromBD ->
            if (newsFromBD.isEmpty()) {
                (apiService.getEvents() + apiService.getEventsFake()).also {
                    putDatabase(it)
                    emit(it)
                }
            } else {
                emit(newsFromBD)
            }
        }
    }.catch {
        (getNewsFromAssets() + getNewsFooFromAssets()).also {
            putDatabase(it)
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

    override fun getNews(id: Int): Flow<News> = flow {
        eventDao.getEvent(id).also { event ->
            emit(mapper.fromEvent(event))
        }
    }.catch {
        getNewsFromAsset(id)
    }.flowOn(Dispatchers.IO)

    override suspend fun putDatabase(listNews: List<News>) {
        eventDao.insertEvents(listNews.map { news ->
            mapper.toEvent(news)
        })
    }

    private fun getNewsFromAsset(id: Int): News {
        return newsAssetsList().find { it.id == id }
            ?: throw NullPointerException("Not found")
    }

    private fun newsAssetsList(): List<News> {
        val combinedList = mutableListOf<News>()
        combinedList.addAll(getNewsFromAssets())
        combinedList.addAll(getNewsFooFromAssets())
        return combinedList.toList()
    }

    private fun getNewsFromAssets(): List<News> =
        assetManager.getNewsListFromAsset("news.json")

    private fun getNewsFooFromAssets(): List<News> =
        assetManager.getNewsListFromAsset("fake_news.json")
}
