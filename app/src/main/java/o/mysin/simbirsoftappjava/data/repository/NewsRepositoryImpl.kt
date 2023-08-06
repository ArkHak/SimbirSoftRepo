package o.mysin.simbirsoftappjava.data.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class NewsRepositoryImpl(
    private val assetManager: AssetManager,
    private val apiService: ApiService,
) : NewsRepository {


//    override fun getFlowableNews(): Flow<List<News>> = flow {
//        val mergeFlow = getNewsFromAssetsByFlow()
//            .combine(getFooByFlow()) { newsList, fooNewsList ->
//                newsList + fooNewsList
//            }
//            .catch { error ->
//                Log.e("MOD_TAG", "getFlowableNews: $error")
//            }
//        emitAll(mergeFlow)
//    }.flowOn(Dispatchers.IO)

//    override fun getObservableNews(): Observable<List<News>> {
//        return Observable.zip(
//            getNewsFromAssetsByRxJava(),
//            getFooByRxJava()
//        ) { list1, list2 ->
//            Log.d(TAG_MOD, "Thread in zip(): ${Thread.currentThread().name}")
//            val combinedList = mutableListOf<News>()
//            combinedList.addAll(list1)
//            combinedList.addAll(list2)
//            combinedList
//        }
//    }

    override fun getObservableNews(): Observable<List<News>> {
        return Observable.zip(
            getNewsFromWeb(),
            getNewsFooFromWeb()
        ) { list1, list2 ->
            val combinedList = mutableListOf<News>()
            combinedList.addAll(list1)
            combinedList.addAll(list2)
            combinedList.toList()
        }
            .onErrorReturnItem(newsAssetsList())
    }

    override fun getObservableNews(id: Int): Observable<News> {
        return apiService.getEventById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturnItem(getNewsFromAsset(id))
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


//    private fun getNewsFromAssetsByFlow(): Flow<List<News>> = flow {
//        try {
//            emit(assetManager.getNewsListFromAsset("news.json"))
//        } catch (error: Exception) {
//            Log.e("MOD_TAG", "getNewsFromAssetsByFlow: $error")
//            emit(emptyList())
//        }
//    }.flowOn(Dispatchers.IO)

    private fun getNewsFromWeb(): Observable<List<News>> {
        return apiService.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getNewsFromAssets(): List<News> {
        return assetManager.getNewsListFromAsset("news.json")
    }

//    private fun getFooByFlow(): Flow<List<News>> = flow {
//        try {
//            emit(assetManager.getNewsListFromAsset("fake_news.json"))
//        } catch (error: Exception) {
//            Log.e("MOD_TAG", "getFooByFlow: $error")
//            emit(emptyList())
//        }
//    }.flowOn(Dispatchers.IO)

    private fun getNewsFooFromWeb(): Observable<List<News>> {
        return apiService.getEventsFake()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getNewsFooFromAssets(): List<News> {
        return assetManager.getNewsListFromAsset("fake_news.json")
    }

}
