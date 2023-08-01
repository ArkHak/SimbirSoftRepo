package o.mysin.simbirsoftappjava.ui.news.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News

class NewsDetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _news: MutableLiveData<News> = MutableLiveData()
    val news: LiveData<News>
        get() = _news

    fun getNews(newsId: Int) {
        viewModelScope.launch {
            newsRepository.getFlowableNews()
                .map { newsList ->
                    newsList.find { it.id == newsId }
                }
                .catch { error ->
                    Log.e("MOD_TAG", "getNews: $error")
                }
                .flowOn(Dispatchers.IO)
                .collect { news ->
                    _news.value = news
                }
        }
    }
}
