package ru.mys_ya.feature_news.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.domain.repository.HelpCategoryRepository
import ru.mys_ya.feature_news.ui.filter.FilterViewModel

@Module
class FilterModule {
    @Provides
    fun provideFilterViewModel(
        repository: HelpCategoryRepository,
    ): FilterViewModel {
        return FilterViewModel(repository)
    }
}
