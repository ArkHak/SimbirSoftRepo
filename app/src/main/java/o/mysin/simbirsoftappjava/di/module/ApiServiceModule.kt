package o.mysin.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.data.network.RetrofitClient
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.create()
    }
}
