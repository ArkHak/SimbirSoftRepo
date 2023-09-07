package ru.mys_ya.feature_news_api.usecase

import ru.mys_ya.feature_news_api.domain.News


fun interface GetNewsListUseCase {
    suspend operator fun invoke(): List<News>
}
