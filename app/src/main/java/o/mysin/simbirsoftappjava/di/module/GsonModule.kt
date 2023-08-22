package o.mysin.simbirsoftappjava.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GsonModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
