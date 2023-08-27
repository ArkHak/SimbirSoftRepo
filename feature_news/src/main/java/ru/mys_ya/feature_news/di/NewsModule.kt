package ru.mys_ya.feature_news.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.domain.repository.HelpCategoryRepository
import ru.mys_ya.core.domain.repository.NewsRepository
import ru.mys_ya.feature_news.ui.news.detail.NewsDetailViewModel
import ru.mys_ya.feature_news.ui.news.main.NewsViewModel

@Module
class NewsModule {
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
}
