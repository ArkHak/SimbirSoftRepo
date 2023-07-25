package o.mysin.simbirsoftappjava.domain.repository

import io.reactivex.rxjava3.core.Observable
import o.mysin.simbirsoftappjava.domain.model.News

interface NewsRepository {
    fun getAllNews(): List<News>
    fun getObservableNews(): Observable<List<News>>
    fun addListNews(listNews: List<News>)
    fun setIsViewedNews(idNews: Int)
    fun unsubscribe()
}
