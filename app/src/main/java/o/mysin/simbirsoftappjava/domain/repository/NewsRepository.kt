package o.mysin.simbirsoftappjava.domain.repository

import io.reactivex.rxjava3.core.Observable
import o.mysin.simbirsoftappjava.domain.model.News

interface NewsRepository {
    fun getObservableNews(): Observable<List<News>>
    fun getObservableNews(id: Int): Observable<News>
}
