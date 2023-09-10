package ru.mys_ya.feature_news.usecase

import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.feature_news_api.domain.News
import ru.mys_ya.feature_news_api.repository.NewsRepository
import ru.mys_ya.feature_news_api.usecase.GetNewsListUseCase
import javax.inject.Inject

class GetNewsListUseCaseImpl @Inject constructor(
    private val repositoryHelpCategory: HelpCategoryRepository,
    private val repositoryNews: NewsRepository,
) : GetNewsListUseCase {
    override suspend fun invoke(): List<News> {
        val filterIdList = repositoryHelpCategory.getIdHelpCategoriesHideList()
        val newsList = mutableListOf<News>()
        repositoryNews.getNews().collect { list ->
            newsList.addAll(getNewsByFilter(list, filterIdList))
        }
        return newsList
    }

    private fun getNewsByFilter(newsList: List<News>, filter: List<Int>): List<News> {
        return newsList.filter { news ->
            news.categories.any { category ->
                category !in filter
            }
        }
    }

}
