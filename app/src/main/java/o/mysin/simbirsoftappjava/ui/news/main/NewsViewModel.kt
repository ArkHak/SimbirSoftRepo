package o.mysin.simbirsoftappjava.ui.news.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>>
        get() = _newsList

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun loadNews() {
        val filterIdList = helpCategoryRepository.getHelpCategories()
            .filter { it.isEnabled }
            .map { it.id }

        val disposable = newsRepository.getObservableNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newsList ->
                _newsList.value = getNewsByFilter(newsList, filterIdList)
            }

        compositeDisposable.add(disposable)
    }

    fun setIsViewedNews(idNews: Int) {
        _newsList.value?.find { it.id == idNews }?.isViewed = true
        newsRepository.setIsViewedNews(idNews)
    }

    private fun getNewsByFilter(newsList: List<News>, filter: List<Int>): List<News> {
        return newsList.filter { news ->
            news.listHelpCategoryId.any() { category ->
                category in filter
            }
        }
    }
}
