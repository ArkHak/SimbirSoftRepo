package ru.mys_ya.feature_news.ui.news.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.core.utils.ErrorMessage
import ru.mys_ya.feature_news_api.domain.News
import ru.mys_ya.feature_news_api.usecase.GetNewsListUseCase
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsListUseCase: GetNewsListUseCase,
) : ViewModel() {

    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>>
        get() = _newsList

    private val _errorMessage: MutableLiveData<ErrorMessage> = MutableLiveData()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    private var listIdNewsViewed = arrayListOf<Int>()

    fun loadNews() {
        viewModelScope.launch {
            try {
                val newsList = newsListUseCase()
                _newsList.value = newsList
            } catch (error: Exception) {
                Log.e("MOD_TAG", "loadNews: $error")
                _errorMessage.value = ErrorMessage("loadNews: $error")
                _newsList.value = emptyList()
            }
        }
    }

    fun setIsViewedNews(idNews: Int) {
        listIdNewsViewed.add(idNews)
    }

    fun getCountNewsNotViewed(newsList: List<News>): Int {
        val currentListNewsSize = newsList.size
        val currentCountNewsViewed =
            newsList.count { news -> listIdNewsViewed.contains(news.id) }
        return currentListNewsSize - currentCountNewsViewed
    }
}
