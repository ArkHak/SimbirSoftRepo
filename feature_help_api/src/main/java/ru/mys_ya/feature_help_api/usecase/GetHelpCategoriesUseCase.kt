package ru.mys_ya.feature_help_api.usecase

import ru.mys_ya.feature_help_api.model.HelpCategory

fun interface GetHelpCategoriesUseCase {
    suspend operator fun invoke(): List<HelpCategory>
}
