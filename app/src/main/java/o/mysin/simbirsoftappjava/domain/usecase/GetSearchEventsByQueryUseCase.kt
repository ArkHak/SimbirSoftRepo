package o.mysin.simbirsoftappjava.domain.usecase

import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.model.SearchEvent

class GetSearchEventsByQueryUseCase {

    fun invoke(list: List<News>, searchQuery: String): List<SearchEvent> {
        val filterList = list.filter { item ->
            item.name.contains(searchQuery, ignoreCase = true)
        }

        return filterList.map { SearchEvent(it.name) }
    }
}
