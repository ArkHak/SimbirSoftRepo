package o.mysin.simbirsoftappjava.data.network

import io.reactivex.rxjava3.core.Observable
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import retrofit2.http.GET

interface ApiService {

    @GET("categories")
    fun getCategories(): Observable<List<HelpCategory>>
}
