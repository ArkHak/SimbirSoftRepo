package o.mysin.simbirsoftappjava.data.repository

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class NewsRepositoryImpl(
    private val assetManager: AssetManager,
) : NewsRepository {

    override fun getObservableNews(): Observable<List<News>> {
        return Observable.zip(
            getNewsFromAssetsByRxJava(),
            getFooByRxJava()
        ) { list1, list2 ->
            Log.d(TAG_MOD, "Thread in zip(): ${Thread.currentThread().name}")
            val combinedList = mutableListOf<News>()
            combinedList.addAll(list1)
            combinedList.addAll(list2)
            combinedList
        }
    }

    private fun getNewsFromAssetsByRxJava(): Observable<List<News>> {
        return Observable.create { emitter ->
            val newsList = assetManager.getNewsListFromAsset("news.json")
            emitter.onNext(newsList)
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .doOnNext {
                Log.d(
                    TAG_MOD,
                    "getNewsFromAssetsByRxJava subscribeOn(Schedulers.io()): ${Thread.currentThread().name}"
                )
            }
            .subscribeOn(Schedulers.computation())
            .doOnNext {
                Log.d(
                    TAG_MOD,
                    "getNewsFromAssetsByRxJava subscribeOn(Schedulers.computation()): ${Thread.currentThread().name}"
                )
            }
            .onErrorReturnItem(emptyList())
    }

    private fun getFooByRxJava(): Observable<List<News>> {
        return Observable.create { emitter ->
            val fooNewsList = assetManager.getNewsListFromAsset("fake_news.json")
            emitter.onNext(fooNewsList)
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .doOnNext {
                Log.d(
                    TAG_MOD,
                    "getFooByRxJava subscribeOn(Schedulers.io()): ${Thread.currentThread().name}"
                )
            }
            .subscribeOn(Schedulers.computation())
            .doOnNext {
                Log.d(
                    TAG_MOD,
                    "getFooByRxJava subscribeOn(Schedulers.computation()): ${Thread.currentThread().name}"
                )
            }
            .onErrorReturnItem(emptyList())
    }

    companion object {
        private const val TAG_MOD = "TAG_MOD"
    }
}
