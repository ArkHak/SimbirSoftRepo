package o.mysin.simbirsoftappjava.ui.news.main

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
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {


    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>>
        get() = _newsList

    private var listIdNewsViewed = arrayListOf<Int>()


    fun loadNews() {
        val filterIdList = helpCategoryRepository.getIdHelpCategoriesHideList()

        viewModelScope.launch {
            newsRepository.getNews()
                .flowOn(Dispatchers.IO)
                .map { newsList ->
                    getNewsByFilter(newsList, filterIdList)
                }
                .catch { error ->
                    Log.e("MOD_TAG", "loadNews: $error")
                    emit(emptyList())
                }
                .collect { newsList ->
                    _newsList.value = newsList
                }
        }
    }

    fun setIsViewedNews(idNews: Int) {
        listIdNewsViewed.add(idNews)
    }

    private fun getNewsByFilter(newsList: List<News>, filter: List<Int>): List<News> {
        return newsList.filter { news ->
            news.categories.any { category ->
                category !in filter
            }
        }
    }

    fun getCountNewsNotViewed(newsList: List<News>): Int {
        val currentListNewsSize = newsList.size
        val currentCountNewsViewed =
            newsList.count { news -> listIdNewsViewed.contains(news.id) }
        return currentListNewsSize - currentCountNewsViewed
    }
}
