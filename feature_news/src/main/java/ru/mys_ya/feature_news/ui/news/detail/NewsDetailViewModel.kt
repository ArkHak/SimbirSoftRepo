package ru.mys_ya.feature_news.ui.news.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.mys_ya.core.domain.model.News
import ru.mys_ya.core.domain.repository.NewsRepository
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _news: MutableLiveData<News> = MutableLiveData()
    val news: LiveData<News>
        get() = _news

    fun loadNews(newsId: Int) {
        viewModelScope.launch {
            newsRepository.getNews(newsId)
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    Log.e("MOD_TAG", "loadNews: $error")
                }
                .collect { news ->
                    _news.value = news
                }
        }
    }
}
