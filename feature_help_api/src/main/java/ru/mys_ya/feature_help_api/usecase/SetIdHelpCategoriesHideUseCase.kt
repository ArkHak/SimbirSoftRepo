package ru.mys_ya.feature_help_api.usecase

fun interface SetIdHelpCategoriesHideUseCase {
    suspend operator fun invoke(idList: ArrayList<Int>)
}
