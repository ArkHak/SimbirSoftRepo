package ru.mys_ya.simbirsoftappjava

import android.app.Application
import ru.mys_ya.simbirsoftappjava.di.AppComponent
import ru.mys_ya.core.di.module.AppModule
import ru.mys_ya.feature_help.di.component.HelpComponent
import ru.mys_ya.feature_help.di.component.HelpComponentProvider
import ru.mys_ya.feature_news.di.component.detail.NewsDetailComponent
import ru.mys_ya.feature_news.di.component.detail.NewsDetailComponentProvider
import ru.mys_ya.feature_news.di.component.worker.SendWorkerComponent
import ru.mys_ya.feature_news.di.component.worker.SendWorkerComponentProvider
import ru.mys_ya.feature_news.di.component.filter.FilterComponent
import ru.mys_ya.feature_news.di.component.filter.FilterComponentProvider
import ru.mys_ya.feature_news.di.component.news.NewsComponent
import ru.mys_ya.feature_news.di.component.news.NewsComponentProvider
import ru.mys_ya.feature_search.di.component.SearchEventComponent
import ru.mys_ya.feature_search.di.component.SearchEventComponentProvider
import ru.mys_ya.simbirsoftappjava.di.DaggerAppComponent

class WantHelpApp :
    Application(),
    HelpComponentProvider,
    SearchEventComponentProvider,
    FilterComponentProvider,
    NewsComponentProvider,
    NewsDetailComponentProvider,
    SendWorkerComponentProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun getHelpComponent(): HelpComponent = appComponent

    override fun getSearchEventFragment(): SearchEventComponent = appComponent

    override fun getFilterComponent(): FilterComponent = appComponent

    override fun getNewsComponent(): NewsComponent = appComponent

    override fun getNewsDetailComponent(): NewsDetailComponent = appComponent
    override fun getSendWorkerComponent(): SendWorkerComponent = appComponent

}
