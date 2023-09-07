package o.mysin.simbirsoftappjava.di

import dagger.Component
import o.mysin.simbirsoftappjava.util.AppScope
import o.mysin.simbirsoftappjava.di.module.ApiServiceModule
import ru.mys_ya.core.di.module.AppModule
import o.mysin.simbirsoftappjava.di.module.AssetManagerModule
import ru.mys_ya.core.di.module.DatabaseModule
import ru.mys_ya.core.di.module.GsonModule
import o.mysin.simbirsoftappjava.di.module.RepositoryModule
import ru.mys_ya.core.di.viewmodule.MultiViewModelFactory
import ru.mys_ya.feature_help.di.HelpComponent
import ru.mys_ya.feature_help.di.HelpMapperModule
import ru.mys_ya.feature_help.di.HelpViewModelModule
import ru.mys_ya.feature_news.di.FilterModule
import ru.mys_ya.feature_news.di.NewsMapperModule
import ru.mys_ya.feature_news.di.NewsUseCaseModule
import ru.mys_ya.feature_news.di.NewsViewModelModule
import ru.mys_ya.feature_news.di.component.detail.NewsDetailComponent
import ru.mys_ya.feature_news.di.component.filter.FilterComponent
import ru.mys_ya.feature_news.di.component.news.NewsComponent
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
        NewsUseCaseModule::class,
        NewsMapperModule::class,
        FilterModule::class,
        HelpViewModelModule::class,
        HelpMapperModule::class
    ],
)
@AppScope
interface AppComponent :
    HelpComponent,
    SearchEventComponent,
    FilterComponent,
    NewsComponent,
    NewsDetailComponent {

    val multiViewModelFactory: MultiViewModelFactory
}
