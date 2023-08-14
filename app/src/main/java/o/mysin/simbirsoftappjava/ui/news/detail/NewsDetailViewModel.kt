package o.mysin.simbirsoftappjava.ui.news.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News

class NewsDetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _news: MutableLiveData<News> = MutableLiveData()
    val news: LiveData<News>
        get() = _news

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loadNews(newsId: Int) {
        val disposable = newsRepository.getObservableNews(newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { news ->
                _news.value = news
            }

        compositeDisposable.add(disposable)
    }

//    fun getNews(newsId: Int) {
//        viewModelScope.launch {
//            newsRepository.getObservableNews()
//                .map { newsList ->
//                    newsList.find { it.id == newsId }
//                }
//                .catch { error ->
//                    Log.e("MOD_TAG", "getNews: $error")
//                }
//                .flowOn(Dispatchers.IO)
//                .collect { news ->
//                    _news.value = news
//                }
//        }
//    }
}
