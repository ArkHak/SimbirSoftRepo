package ru.mys_ya.feature_news_api.usecase

import ru.mys_ya.core.domain.model.SearchEvent

fun interface GetSearchEventsByQueryUseCase {
    suspend operator fun invoke(searchQuery: String): List<SearchEvent>
}
