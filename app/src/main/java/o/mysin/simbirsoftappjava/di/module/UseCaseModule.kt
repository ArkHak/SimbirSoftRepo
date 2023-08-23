package o.mysin.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.usecase.GetSearchEventsByQueryUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideSearchEventsByQueryUseCase(
        repository: NewsRepository,
    ): GetSearchEventsByQueryUseCase {
        return GetSearchEventsByQueryUseCase(repository)
    }
}
