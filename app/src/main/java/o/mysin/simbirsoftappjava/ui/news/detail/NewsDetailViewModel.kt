package o.mysin.simbirsoftappjava.ui.news.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News

class NewsDetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _news: MutableLiveData<News> = MutableLiveData()
    val news: LiveData<News>
        get() = _news

    fun getNews(newsId: Int) {
        viewModelScope.launch {
            _news.value = newsRepository.getAllNews().find { it.id == newsId }
        }
    }

    override fun onCleared() {
        super.onCleared()
        newsRepository.unsubscribe()
    }
}
