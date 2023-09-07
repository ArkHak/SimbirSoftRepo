package ru.mys_ya.feature_news.usecase

import kotlinx.coroutines.flow.first
import ru.mys_ya.feature_news_api.domain.News
import ru.mys_ya.feature_news_api.repository.NewsRepository
import ru.mys_ya.feature_news_api.usecase.GetNewsDetailUseCase
import javax.inject.Inject

class GetNewsDetailUseCaseImpl @Inject constructor(
    private val repositoryNews: NewsRepository,
) : GetNewsDetailUseCase {

    override suspend fun invoke(newsId: Int): News {
        return repositoryNews.getNews(newsId).first()
    }

}
