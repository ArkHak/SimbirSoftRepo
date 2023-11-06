package ru.mys_ya.feature_help_api.usecase

fun interface GetIdHelpCategoriesHideUseCase {
    suspend operator fun invoke(): List<Int>
}
