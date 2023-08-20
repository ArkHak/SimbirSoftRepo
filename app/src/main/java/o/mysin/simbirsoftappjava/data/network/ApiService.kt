package o.mysin.simbirsoftappjava.data.network

import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.model.News
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("categories")
    suspend fun getCategories(): List<HelpCategory>

    @GET("events")
    suspend fun getEvents(): List<News>

    @GET("events_fake")
    suspend fun getEventsFake(): List<News>

    @POST("events")
    suspend fun getEventById(@Query("id") id: Int): News

}
