package ru.mys_ya.feature_help.usecase

import ru.mys_ya.feature_help_api.model.HelpCategory
import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.feature_help_api.usecase.GetHelpCategoriesUseCase
import javax.inject.Inject

class GetHelpCategoriesUseCaseImpl @Inject constructor(
    private val repositoryHelpCategory: HelpCategoryRepository,
) : GetHelpCategoriesUseCase {
    override suspend fun invoke(): List<HelpCategory> {
        val helpCategoryList = mutableListOf<HelpCategory>()
        repositoryHelpCategory.getHelpCategories().collect { helpList ->
            helpCategoryList.addAll(helpList)
        }
        return helpCategoryList
    }
}
