package o.mysin.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import o.mysin.simbirsoftappjava.ui.filter.FilterViewModel
import o.mysin.simbirsoftappjava.ui.help.HelpViewModel
import o.mysin.simbirsoftappjava.ui.news.detail.NewsDetailViewModel
import o.mysin.simbirsoftappjava.ui.news.main.NewsViewModel
import o.mysin.simbirsoftappjava.ui.search.events.SearchEventsViewModel
import ru.mys_ya.core.domain.repository.HelpCategoryRepository
import ru.mys_ya.core.domain.repository.NewsRepository
import ru.mys_ya.core.domain.usecase.GetSearchEventsByQueryUseCase

@Module
class ViewModelModule {

    @Provides
    fun provideHelpViewModel(
        repository: HelpCategoryRepository,
    ): HelpViewModel {
        return HelpViewModel(repository)
    }

    @Provides
    fun provideFilterViewModel(
        repository: HelpCategoryRepository,
    ): FilterViewModel {
        return FilterViewModel(repository)
    }

    @Provides
    fun provideNewsViewModel(
        newsRepository: NewsRepository,
        helpCategoryRepository: HelpCategoryRepository,
    ): NewsViewModel {
        return NewsViewModel(newsRepository, helpCategoryRepository)
    }

    @Provides
    fun provideNewsDetailViewModel(
        repository: NewsRepository,
    ): NewsDetailViewModel {
        return NewsDetailViewModel(repository)
    }

    @Provides
    fun provideSearchEventsViewModel(
        useCase: GetSearchEventsByQueryUseCase,
    ): SearchEventsViewModel {
        return SearchEventsViewModel(useCase)
    }

}
