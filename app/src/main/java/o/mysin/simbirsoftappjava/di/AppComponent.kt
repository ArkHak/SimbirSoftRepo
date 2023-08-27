package o.mysin.simbirsoftappjava.di

import dagger.Component
import ru.mys_ya.core.di.module.ApiServiceModule
import ru.mys_ya.core.di.module.AppModule
import ru.mys_ya.core.di.module.AssetManagerModule
import ru.mys_ya.core.di.module.DatabaseModule
import ru.mys_ya.core.di.module.GsonModule
import ru.mys_ya.core.di.module.MapperModule
import ru.mys_ya.core.di.module.RepositoryModule
import ru.mys_ya.core.di.module.UseCaseModule
import ru.mys_ya.feature_news.ui.filter.FilterFragment
import ru.mys_ya.feature_help.ui.HelpFragment
import ru.mys_ya.feature_news.ui.news.detail.NewsDetailFragment
import ru.mys_ya.feature_news.ui.news.main.NewsFragment
import ru.mys_ya.feature_search.ui.events.SearchEventsFragment
import ru.mys_ya.feature_help.component.HelpComponent
import ru.mys_ya.feature_help.di.HelpModule
import ru.mys_ya.feature_news.component.detail.NewsDetailComponent
import ru.mys_ya.feature_news.component.filter.FilterComponent
import ru.mys_ya.feature_news.component.news.NewsComponent
import ru.mys_ya.feature_news.di.FilterModule
import ru.mys_ya.feature_news.di.NewsModule
import ru.mys_ya.feature_search.component.SearchEventComponent
import ru.mys_ya.feature_search.di.SearchModule
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
        FilterModule::class
    ]
)
interface AppComponent :
    HelpComponent,
    SearchEventComponent,
    FilterComponent,
    NewsComponent,
    NewsDetailComponent {
    override fun injectHelpFragment(helpFragment: HelpFragment)
    override fun injectSearchEventFragment(searchEventFragment: SearchEventsFragment)
    override fun injectFilterFragment(filterFragment: FilterFragment)
    override fun injectNewsFragment(newsFragment: NewsFragment)
    override fun injectNewsDetailFragment(newsDetailFragment: NewsDetailFragment)
}
