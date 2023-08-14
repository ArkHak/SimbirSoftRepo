package o.mysin.simbirsoftappjava.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class NewsRepositoryImpl(
    private val assetManager: AssetManager,
    private val apiService: ApiService,
) : NewsRepository {

    override fun getNews(): Flow<List<News>> = flow {
        emit(apiService.getEvents() + apiService.getEventsFake())
    }.catch {
        getNewsFromAssets() + getNewsFooFromAssets()
    }.flowOn(Dispatchers.IO)

    override fun getNews(id: Int): Flow<News> = flow {
        emit(apiService.getEventById(id))
    }.catch {
        getNewsFromAsset(id)
    }.flowOn(Dispatchers.IO)

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

    private fun getNewsFromAssets(): List<News> {
        return assetManager.getNewsListFromAsset("news.json")
    }

    private fun getNewsFooFromAssets(): List<News> {
        return assetManager.getNewsListFromAsset("fake_news.json")
    }

}
