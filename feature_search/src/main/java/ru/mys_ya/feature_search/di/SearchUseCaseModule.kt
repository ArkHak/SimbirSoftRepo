package ru.mys_ya.feature_search.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.feature_news_api.repository.NewsRepository
import ru.mys_ya.feature_search.usecase.GetSearchEventsByQueryUseCaseImpl
import ru.mys_ya.feature_search_api.usecase.GetSearchEventsByQueryUseCase

@Module
class SearchUseCaseModule {
    @Provides
    fun provideSearchEventsByQueryUseCase(
        repository: NewsRepository,
    ): GetSearchEventsByQueryUseCase {
        return GetSearchEventsByQueryUseCaseImpl(repository)
    }
}
