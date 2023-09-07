package ru.mys_ya.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.mys_ya.feature_help_api.model.HelpCategory
import ru.mys_ya.feature_news_api.domain.News

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
