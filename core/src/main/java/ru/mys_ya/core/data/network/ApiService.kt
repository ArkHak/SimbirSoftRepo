package ru.mys_ya.core.data.network

import ru.mys_ya.core.domain.model.HelpCategory
import ru.mys_ya.core.domain.model.News
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
