package o.mysin.simbirsoftappjava.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.utils.AssetManager
import java.lang.Exception

class NewsRepositoryImpl(
    private val assetManager: AssetManager,
) : NewsRepository {

    override fun getFlowableNews(): Flow<List<News>> = flow {
        val mergeFlow = getNewsFromAssetsByFlow()
            .combine(getFooByFlow()) { newsList, fooNewsList ->
                newsList + fooNewsList
            }
            .catch { error ->
                Log.e("MOD_TAG", "getFlowableNews: $error")
            }
        emitAll(mergeFlow)
    }.flowOn(Dispatchers.IO)

    private fun getNewsFromAssetsByFlow(): Flow<List<News>> = flow {
        try {
            emit(assetManager.getNewsListFromAsset("news.json"))
        } catch (error: Exception) {
            Log.e("MOD_TAG", "getNewsFromAssetsByFlow: $error")
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)


    private fun getFooByFlow(): Flow<List<News>> = flow {
        try {
            emit(assetManager.getNewsListFromAsset("fake_news.json"))
        } catch (error: Exception) {
            Log.e("MOD_TAG", "getFooByFlow: $error")
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

}
