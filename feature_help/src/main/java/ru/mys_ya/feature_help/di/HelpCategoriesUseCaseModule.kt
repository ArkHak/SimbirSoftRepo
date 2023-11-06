package ru.mys_ya.feature_help.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.feature_help.usecase.GetHelpCategoriesUseCaseImpl
import ru.mys_ya.feature_help.usecase.GetIdHelpCategoriesHideUseCaseImpl
import ru.mys_ya.feature_help.usecase.SetIdHelpCategoriesHideUseCaseImpl
import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.feature_help_api.usecase.GetHelpCategoriesUseCase
import ru.mys_ya.feature_help_api.usecase.GetIdHelpCategoriesHideUseCase
import ru.mys_ya.feature_help_api.usecase.SetIdHelpCategoriesHideUseCase

@Module
class HelpCategoriesUseCaseModule {
    @Provides
    fun provideHelpCategoriesListUseCase(
        repository: HelpCategoryRepository,
    ): GetHelpCategoriesUseCase {
        return GetHelpCategoriesUseCaseImpl(repository)
    }

    @Provides
    fun provideGetterHelpCategoriesIdHideUseCase(
        repository: HelpCategoryRepository
    ) : GetIdHelpCategoriesHideUseCase {
        return GetIdHelpCategoriesHideUseCaseImpl(repository)
    }

    @Provides
    fun provideSetterHelpCategoriesIdHideUseCase(
        repository: HelpCategoryRepository
    ) : SetIdHelpCategoriesHideUseCase {
        return SetIdHelpCategoriesHideUseCaseImpl(repository)
    }
}
