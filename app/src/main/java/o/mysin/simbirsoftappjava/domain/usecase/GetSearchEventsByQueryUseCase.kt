package o.mysin.simbirsoftappjava.domain.usecase

import o.mysin.simbirsoftappjava.domain.model.SearchEvent
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository

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
