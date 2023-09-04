package o.mysin.simbirsoftappjava.di

import dagger.Component
import ru.mys_ya.core.di.module.*
import ru.mys_ya.core.di.viewModel.MultiViewModelFactory
import ru.mys_ya.feature_help.di.HelpModule
import ru.mys_ya.feature_help.di.component.HelpComponent
import ru.mys_ya.feature_news.di.BindFilterModule
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
        HelpModule::class,
        SearchModule::class,
        NewsModule::class,
        BindFilterModule::class
    ]
)
interface AppComponent :
    HelpComponent,
    SearchEventComponent,
    FilterComponent,
    NewsComponent,
    NewsDetailComponent {

    /** (4)
     * Говорим даггеру, что нам в компоненте нужен объект MultiViewModelFactory, чтобы куда-нибудь
     * его заинжектить
     */
    val multiViewModelFactory: MultiViewModelFactory

}
