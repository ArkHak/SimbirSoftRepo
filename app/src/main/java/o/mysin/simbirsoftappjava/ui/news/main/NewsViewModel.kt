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
import ru.mys_ya.core.domain.repository.NewsRepository
import ru.mys_ya.core.domain.model.News
import ru.mys_ya.core.domain.repository.HelpCategoryRepository
import ru.mys_ya.core.utils.ErrorMessage

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {


    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>>
        get() = _newsList

    private val _errorMessage: MutableLiveData<ErrorMessage> = MutableLiveData()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

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
                    _errorMessage.value = ErrorMessage("loadNews: $error")
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
