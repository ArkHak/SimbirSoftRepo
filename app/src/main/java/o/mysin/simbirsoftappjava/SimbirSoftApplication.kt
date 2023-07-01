package o.mysin.simbirsoftappjava

import android.app.Application
import o.mysin.simbirsoftappjava.di.appModule
import o.mysin.simbirsoftappjava.di.gsonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SimbirSoftApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SimbirSoftApplication)
            modules(
                appModule,
                gsonModule,
            )
        }
    }
}
