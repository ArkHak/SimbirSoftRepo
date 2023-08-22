package o.mysin.simbirsoftappjava.di

import dagger.Component
import o.mysin.simbirsoftappjava.di.module.ApiServiceModule
import o.mysin.simbirsoftappjava.di.module.AppModule
import o.mysin.simbirsoftappjava.di.module.AssetManagerModule
import o.mysin.simbirsoftappjava.di.module.DatabaseModule
import o.mysin.simbirsoftappjava.di.module.GsonModule
import o.mysin.simbirsoftappjava.di.module.MapperModule
import o.mysin.simbirsoftappjava.di.module.RepositoryModule
import o.mysin.simbirsoftappjava.di.module.ViewModelModule
import o.mysin.simbirsoftappjava.ui.filter.FilterFragment
import o.mysin.simbirsoftappjava.ui.help.HelpFragment
import o.mysin.simbirsoftappjava.ui.news.detail.NewsDetailFragment
import o.mysin.simbirsoftappjava.ui.news.main.NewsFragment
import o.mysin.simbirsoftappjava.ui.search.events.SearchEventsFragment
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
    ]
)
interface AppComponent {
    fun inject(fragment: HelpFragment)
    fun inject(fragment: FilterFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: NewsDetailFragment)
    fun inject(fragment: SearchEventsFragment)
}
