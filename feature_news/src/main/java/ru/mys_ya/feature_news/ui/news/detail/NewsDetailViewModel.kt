package ru.mys_ya.feature_news.ui.news.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.feature_news_api.domain.News
import ru.mys_ya.feature_news_api.usecase.GetNewsDetailUseCase
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(
    private val newsDetailUseCase: GetNewsDetailUseCase,
) : ViewModel() {

    private val _news: MutableLiveData<News> = MutableLiveData()
    val news: LiveData<News>
        get() = _news

    fun loadNews(newsId: Int) {
        viewModelScope.launch {
            try {
                _news.value = newsDetailUseCase.invoke(newsId)
            } catch (error: Exception) {
                Log.e("MOD_TAG", "loadNews: $error")
            }
        }
    }
}
