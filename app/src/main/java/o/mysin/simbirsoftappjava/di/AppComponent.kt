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
import o.mysin.simbirsoftappjava.ui.search.events.SearchEventsFragment
import ru.mys_ya.feature_help.component.HelpComponent
import ru.mys_ya.feature_help.di.HelpModule
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
        HelpModule::class
    ]
)
interface AppComponent : HelpComponent {
    override fun injectHelpFragment(helpFragment: HelpFragment)
    fun inject(fragment: FilterFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: NewsDetailFragment)
    fun inject(fragment: SearchEventsFragment)
}
