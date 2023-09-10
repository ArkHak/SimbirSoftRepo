package ru.mys_ya.feature_news_api.usecase

import ru.mys_ya.feature_news_api.domain.News


fun interface GetNewsDetailUseCase {
    suspend operator fun invoke(newsId: Int): News
}
