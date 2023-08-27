package ru.mys_ya.core.di.module

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.domain.repository.NewsRepository
import ru.mys_ya.core.domain.usecase.GetSearchEventsByQueryUseCase
import ru.mys_ya.core.domain.usecase.GetSearchEventsByQueryUseCaseImpl

@Module
class UseCaseModule {

    @Provides
    fun provideSearchEventsByQueryUseCase(
        repository: NewsRepository,
    ): GetSearchEventsByQueryUseCase {
        return GetSearchEventsByQueryUseCaseImpl(repository)
    }
}
