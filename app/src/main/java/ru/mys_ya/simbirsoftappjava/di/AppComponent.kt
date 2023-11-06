package ru.mys_ya.simbirsoftappjava.di

import dagger.Component
import ru.mys_ya.simbirsoftappjava.util.AppScope
import ru.mys_ya.simbirsoftappjava.di.module.ApiServiceModule
import ru.mys_ya.core.di.module.AppModule
import ru.mys_ya.simbirsoftappjava.di.module.AssetManagerModule
import ru.mys_ya.simbirsoftappjava.di.module.NotificationModule
import ru.mys_ya.core.di.module.DatabaseModule
import ru.mys_ya.core.di.module.GsonModule
import ru.mys_ya.simbirsoftappjava.di.module.RepositoryModule
import ru.mys_ya.simbirsoftappjava.di.module.WorkManagerModule
import ru.mys_ya.core.di.viewmodule.MultiViewModelFactory
import ru.mys_ya.feature_help.di.HelpCategoriesUseCaseModule
import ru.mys_ya.feature_help.di.component.HelpComponent
import ru.mys_ya.feature_help.di.HelpMapperModule
import ru.mys_ya.feature_help.di.HelpViewModelModule
import ru.mys_ya.feature_news.di.FilterModule
import ru.mys_ya.feature_news.di.NewsUseCaseModule
import ru.mys_ya.feature_news.di.NewsMapperModule
import ru.mys_ya.feature_search.di.SearchUseCaseModule
import ru.mys_ya.feature_news.di.NewsViewModelModule
import ru.mys_ya.feature_news.di.component.detail.NewsDetailComponent
import ru.mys_ya.feature_news.di.component.worker.WorkerComponent
import ru.mys_ya.feature_news.di.component.filter.FilterComponent
import ru.mys_ya.feature_news.di.component.news.NewsComponent
import ru.mys_ya.feature_news_api.util.NotificationComponent
import ru.mys_ya.feature_search.di.SearchModule
import ru.mys_ya.feature_search.di.component.SearchEventComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiServiceModule::class,
        AppModule::class,
        AssetManagerModule::class,
        DatabaseModule::class,
        GsonModule::class,
        RepositoryModule::class,
        SearchModule::class,
        NewsViewModelModule::class,
        NewsMapperModule::class,
        FilterModule::class,
        HelpViewModelModule::class,
        HelpMapperModule::class,
        SearchUseCaseModule::class,
        NewsUseCaseModule::class,
        HelpCategoriesUseCaseModule::class,
        WorkManagerModule::class,
        NotificationModule::class,
    ],
)
@AppScope
interface AppComponent :
    HelpComponent,
    SearchEventComponent,
    FilterComponent,
    NewsComponent,
    NewsDetailComponent,
    WorkerComponent {

    val multiViewModelFactory: MultiViewModelFactory

    fun notificationComponent(): NotificationComponent

}
