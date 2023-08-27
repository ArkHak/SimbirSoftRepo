package ru.mys_ya.feature_help.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.domain.repository.HelpCategoryRepository
import ru.mys_ya.feature_help.ui.HelpViewModel

@Module
class HelpModule {

    @Provides
    fun provideHelpViewModel(
        repository: HelpCategoryRepository,
    ): HelpViewModel {
        return HelpViewModel(repository)
    }

}
