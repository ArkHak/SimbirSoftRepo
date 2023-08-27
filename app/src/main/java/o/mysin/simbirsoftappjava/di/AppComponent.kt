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
import o.mysin.simbirsoftappjava.di.module.ViewModelModule
import o.mysin.simbirsoftappjava.ui.filter.FilterFragment
import ru.mys_ya.feature_help.ui.HelpFragment
import o.mysin.simbirsoftappjava.ui.news.detail.NewsDetailFragment
import o.mysin.simbirsoftappjava.ui.news.main.NewsFragment
import ru.mys_ya.feature_search.ui.events.SearchEventsFragment
import ru.mys_ya.feature_help.component.HelpComponent
import ru.mys_ya.feature_help.di.HelpModule
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
        ViewModelModule::class,
        UseCaseModule::class,
        HelpModule::class,
        SearchModule::class,
    ]
)
interface AppComponent : HelpComponent, SearchEventComponent {
    override fun injectHelpFragment(helpFragment: HelpFragment)
    override fun injectSearchEventFragment(searchEventFragment: SearchEventsFragment)
    fun inject(fragment: FilterFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: NewsDetailFragment)
}
