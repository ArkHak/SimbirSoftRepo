package o.mysin.simbirsoftappjava.di

import dagger.Component
import o.mysin.simbirsoftappjava.util.AppScope
import ru.mys_ya.core.di.module.ApiServiceModule
import ru.mys_ya.core.di.module.AppModule
import ru.mys_ya.core.di.module.AssetManagerModule
import ru.mys_ya.core.di.module.DatabaseModule
import ru.mys_ya.core.di.module.GsonModule
import ru.mys_ya.core.di.module.MapperModule
import ru.mys_ya.core.di.module.RepositoryModule
import ru.mys_ya.core.di.module.UseCaseModule
import ru.mys_ya.core.di.viewmodule.MultiViewModelFactory
import ru.mys_ya.feature_help.di.HelpComponent
import ru.mys_ya.feature_help.di.HelpModule
import ru.mys_ya.feature_news.di.FilterModule
import ru.mys_ya.feature_news.di.NewsModule
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
        MapperModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        SearchModule::class,
        NewsModule::class,
        FilterModule::class,
        HelpModule::class,
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
