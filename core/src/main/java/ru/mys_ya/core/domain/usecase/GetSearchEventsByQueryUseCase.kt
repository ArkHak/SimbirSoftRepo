package ru.mys_ya.core.domain.usecase

import ru.mys_ya.core.domain.model.SearchEvent
import ru.mys_ya.core.domain.repository.NewsRepository

class GetSearchEventsByQueryUseCase(private val repository: NewsRepository) {

    suspend fun invoke(searchQuery: String): List<SearchEvent> {
        val filterList = mutableListOf<SearchEvent>()
        repository.getNews().collect { newsList ->
            newsList.filter { item ->
                item.name.contains(searchQuery, ignoreCase = true)
            }.map { item ->
                filterList.add(SearchEvent(item.name))
            }
        }

        return filterList
    }
}
