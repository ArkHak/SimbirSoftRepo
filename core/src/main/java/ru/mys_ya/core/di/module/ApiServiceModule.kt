package ru.mys_ya.core.di.module

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.data.network.ApiService
import ru.mys_ya.core.data.network.RetrofitClient
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.create()
    }
}
