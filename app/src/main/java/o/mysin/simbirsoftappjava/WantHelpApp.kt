package o.mysin.simbirsoftappjava

import android.app.Application
import android.content.Context
import o.mysin.simbirsoftappjava.di.AppComponent
import o.mysin.simbirsoftappjava.di.DaggerAppComponent
import o.mysin.simbirsoftappjava.di.module.AppModule

class WantHelpApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is WantHelpApp -> appComponent
        else -> applicationContext.appComponent
    }
