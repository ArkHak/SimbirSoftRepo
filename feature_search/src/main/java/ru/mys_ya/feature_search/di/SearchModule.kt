package ru.mys_ya.feature_search.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.domain.usecase.GetSearchEventsByQueryUseCase
import ru.mys_ya.feature_search.ui.events.SearchEventsViewModel

@Module
class SearchModule {

    @Provides
    fun provideSearchEventsViewModel(
        useCase: GetSearchEventsByQueryUseCase,
    ): SearchEventsViewModel {
        return SearchEventsViewModel(useCase)
    }

}
