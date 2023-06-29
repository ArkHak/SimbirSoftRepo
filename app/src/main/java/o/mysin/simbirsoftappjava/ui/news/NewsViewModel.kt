package o.mysin.simbirsoftappjava.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.data.db.HelpCategoryRepository
import o.mysin.simbirsoftappjava.data.db.NewsRepository
import o.mysin.simbirsoftappjava.data.entity.News

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>>
        get() = _newsList

    fun loadNews() {
        val filterIdList = helpCategoryRepository.getHelpCategory()
            .filter { it.isEnabled }
            .map { it.id }
        viewModelScope.launch {
            _newsList.value = newsRepository.getNewsByFilter(filterIdList)
        }
    }
}
