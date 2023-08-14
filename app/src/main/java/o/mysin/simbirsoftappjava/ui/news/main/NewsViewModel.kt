package o.mysin.simbirsoftappjava.ui.news.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val helpCategoryRepository: HelpCategoryRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>>
        get() = _newsList

    private var listIdNewsViewed = arrayListOf<Int>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun loadNews() {
        val filterIdList = helpCategoryRepository.getIdHelpCategoriesHideList()

        val disposable = newsRepository.getObservableNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newsList ->
                _newsList.value = getNewsByFilter(newsList, filterIdList)
            }

        compositeDisposable.add(disposable)
    }

//    fun loadNews() {
//        val filterIdList = helpCategoryRepository.getIdHelpCategoriesHideList()
//
//        viewModelScope.launch {
//            newsRepository.getObservableNews()
//                .map { newsList ->
//                    getNewsByFilter(newsList, filterIdList)
//                }
//                .catch { error ->
//                    Log.e("MOD_TAG", "loadNews: $error")
//                    emit(emptyList())
//                }
//                .flowOn(Dispatchers.IO)
//                .collect { newsList ->
//                    _newsList.value = newsList
//                }
//        }
//    }

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
