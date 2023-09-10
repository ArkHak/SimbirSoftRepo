package ru.mys_ya.feature_news.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.feature_news.usecase.GetNewsDetailUseCaseImpl
import ru.mys_ya.feature_news.usecase.GetNewsListUseCaseImpl
import ru.mys_ya.feature_news_api.repository.NewsRepository
import ru.mys_ya.feature_news_api.usecase.GetNewsDetailUseCase
import ru.mys_ya.feature_news_api.usecase.GetNewsListUseCase

@Module
class NewsUseCaseModule {
    @Provides
    fun provideNewsDetailUseCase(
        repositoryNews: NewsRepository,
    ): GetNewsDetailUseCase {
        return GetNewsDetailUseCaseImpl(repositoryNews)
    }

    @Provides
    fun provideNewsListUseCase(
        repositoryHelpCategory: HelpCategoryRepository,
        repositoryNews: NewsRepository,
    ): GetNewsListUseCase {
        return GetNewsListUseCaseImpl(repositoryHelpCategory, repositoryNews)
    }
}
