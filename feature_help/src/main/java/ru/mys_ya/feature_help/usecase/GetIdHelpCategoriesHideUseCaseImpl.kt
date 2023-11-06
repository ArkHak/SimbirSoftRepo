package ru.mys_ya.feature_help.usecase

import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.feature_help_api.usecase.GetIdHelpCategoriesHideUseCase
import javax.inject.Inject

class GetIdHelpCategoriesHideUseCaseImpl @Inject constructor(
    private val repositoryHelpCategory: HelpCategoryRepository,
) : GetIdHelpCategoriesHideUseCase {
    override suspend fun invoke(): List<Int> {
        return repositoryHelpCategory.getIdHelpCategoriesHideList()
    }
}
