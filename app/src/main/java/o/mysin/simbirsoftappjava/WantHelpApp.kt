package o.mysin.simbirsoftappjava

import android.app.Application
import android.content.Context
import o.mysin.simbirsoftappjava.di.AppComponent
import o.mysin.simbirsoftappjava.di.DaggerAppComponent
import ru.mys_ya.core.di.module.AppModule
import ru.mys_ya.feature_help.component.HelpComponent
import ru.mys_ya.feature_help.component.HelpComponentProvider
import ru.mys_ya.feature_search.component.SearchEventComponent
import ru.mys_ya.feature_search.component.SearchEventComponentProvider

class WantHelpApp : Application(), HelpComponentProvider, SearchEventComponentProvider {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }

    override fun getHelpComponent(): HelpComponent {
        return appComponent
    }

    override fun getSearchEventFragment(): SearchEventComponent {
        return appComponent
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is WantHelpApp -> appComponent
        else -> applicationContext.appComponent
    }
