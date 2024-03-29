package ru.mys_ya.feature_search.usecase

import ru.mys_ya.feature_news_api.repository.NewsRepository
import ru.mys_ya.feature_search_api.model.SearchEvent
import ru.mys_ya.feature_search_api.usecase.GetSearchEventsByQueryUseCase
import javax.inject.Inject

class GetSearchEventsByQueryUseCaseImpl @Inject constructor(
    private val repository: NewsRepository,
) : GetSearchEventsByQueryUseCase {

    override suspend operator fun invoke(searchQuery: String): List<SearchEvent> {
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
