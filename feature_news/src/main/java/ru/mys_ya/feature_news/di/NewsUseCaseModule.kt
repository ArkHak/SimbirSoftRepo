package ru.mys_ya.feature_news.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.feature_news.usecase.GetSearchEventsByQueryUseCaseImpl
import ru.mys_ya.feature_news_api.repository.NewsRepository
import ru.mys_ya.feature_news_api.usecase.GetSearchEventsByQueryUseCase

@Module
object NewsUseCaseModule {
    @Provides
    fun provideSearchEventsByQueryUseCase(
        repository: NewsRepository,
    ): GetSearchEventsByQueryUseCase {
        return GetSearchEventsByQueryUseCaseImpl(repository)
    }
}
