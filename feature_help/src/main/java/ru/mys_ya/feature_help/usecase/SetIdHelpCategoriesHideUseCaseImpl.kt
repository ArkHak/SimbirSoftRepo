package ru.mys_ya.feature_help.usecase

import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.feature_help_api.usecase.SetIdHelpCategoriesHideUseCase
import javax.inject.Inject

class SetIdHelpCategoriesHideUseCaseImpl @Inject constructor(
    private val repositoryHelpCategory: HelpCategoryRepository,
): SetIdHelpCategoriesHideUseCase {
    override suspend fun invoke(idList: ArrayList<Int>) {
        repositoryHelpCategory.setIdHelpCategoriesHideList(idList)
    }
}
