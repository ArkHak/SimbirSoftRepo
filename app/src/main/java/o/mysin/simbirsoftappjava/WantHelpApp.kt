package o.mysin.simbirsoftappjava

import android.app.Application
import o.mysin.simbirsoftappjava.di.assetManagerModule
import o.mysin.simbirsoftappjava.di.databaseModule
import o.mysin.simbirsoftappjava.di.repositoryModule
import o.mysin.simbirsoftappjava.di.gsonModule
import o.mysin.simbirsoftappjava.di.retrofitModule
import o.mysin.simbirsoftappjava.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WantHelpApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WantHelpApp)
            modules(
                repositoryModule,
                viewModelModule,
                gsonModule,
                assetManagerModule,
                retrofitModule,
                databaseModule
            )
        }
    }
}
