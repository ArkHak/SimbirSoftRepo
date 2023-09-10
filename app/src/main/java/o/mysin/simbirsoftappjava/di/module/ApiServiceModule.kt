package o.mysin.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import ru.mys_ya.network.ApiService
import ru.mys_ya.network.RetrofitClient
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.create()
    }
}
