package o.mysin.simbirsoftappjava.ui.news.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.data.datasource.local.NewsService
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News

class NewsViewModel(
    private val context: Context,
    private val newsRepository: NewsRepository,
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>>
        get() = _newsList

    private val receiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun onReceive(context: Context, intent: Intent) {
            val newsListFromService: ArrayList<News> =
                intent.getParcelableArrayListExtra(
                    NewsService.NEWS_LIST,
                    News::class.java
                ) as ArrayList<News>
            newsRepository.addListNews(newsListFromService)
            loadNews()
        }
    }

    fun startNewsService() {
        val intent = Intent(context, NewsService::class.java)
        context.startService(intent)
    }

    fun registerReceiver() {
        val filter = IntentFilter(NewsService.NEWS_SERVICE)
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter)
    }

    fun unregisterReceiver() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
    }

    fun loadNews() {
        val filterIdList = helpCategoryRepository.getHelpCategories()
            .filter { it.isEnabled }
            .map { it.id }
        viewModelScope.launch {
            _newsList.value = getNewsByFilter(filterIdList)
        }
    }

    private fun getNewsByFilter(filter: List<Int>): List<News> {
        return newsRepository.getAllNews().filter { news ->
            news.listHelpCategoryId.any() { category ->
                category in filter
            }
        }
    }
}
