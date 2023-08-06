package o.mysin.simbirsoftappjava.data.network

import io.reactivex.rxjava3.core.Observable
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.model.News
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("categories")
    fun getCategories(): Observable<List<HelpCategory>>

    @GET("events")
    fun getEvents(): Observable<List<News>>

    @GET("events_fake")
    fun getEventsFake(): Observable<List<News>>

    @POST("events")
    fun getEventById(@Query("id") id: Int): Observable<News>

}
