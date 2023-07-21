package o.mysin.simbirsoftappjava.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import java.io.InputStream
import java.io.InputStreamReader

@SuppressLint("CheckResult")
class NewsRepositoryImpl(
    private val gson: Gson,
    private val inputStreamOriginal: InputStream,
    private val inputStreamFake: InputStream,
) : NewsRepository {

    private var _listNews = emptyList<News>()

    private val newsSubject: BehaviorSubject<List<News>> = BehaviorSubject.create()
    init {
        Observable.zip(
            getNewsFromAssetsByRxJava(),
            getFooByRxJava()
        ) { list1, list2 -> list1 + list2 }
            .observeOn(Schedulers.io())
            .subscribe { data ->
                Log.d(TAG_MOD, "Thread in zip(): ${Thread.currentThread().name}")
                _listNews = data
                newsSubject.onNext(data)
            }
    }

    override fun getAllNews(): List<News> {
        return _listNews
    }

    override fun getObservableNews(): Observable<List<News>> {
        return newsSubject
    }

    override fun addListNews(listNews: List<News>) {
        _listNews = listNews
    }

    override fun setIsViewedNews(idNews: Int) {
        _listNews.find { it.id == idNews }?.isViewed = true
    }

    private fun getNewsFromAssetsByRxJava(): Observable<List<News>> {
        return Observable.create { emitter ->
            val reader = InputStreamReader(inputStreamOriginal)
            val listNews = gson.fromJson(reader, Array<News>::class.java).toList()
            reader.close()
            inputStreamOriginal.close()
            emitter.onNext(listNews)
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
            val reader = InputStreamReader(inputStreamFake)
            val listNews = gson.fromJson(reader, Array<News>::class.java).toList()
            reader.close()
            inputStreamFake.close()
            emitter.onNext(listNews)
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
