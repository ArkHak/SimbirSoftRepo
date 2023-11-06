package ru.mys_ya.feature_search_api.usecase

import ru.mys_ya.feature_search_api.model.SearchEvent


fun interface GetSearchEventsByQueryUseCase {
    suspend operator fun invoke(searchQuery: String): List<SearchEvent>
}
