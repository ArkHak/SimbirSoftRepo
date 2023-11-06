package ru.mys_ya.feature_news_api.mapper

import ru.mys_ya.core.database.entity.Event
import ru.mys_ya.feature_news_api.domain.News

interface EventMapper {
    fun toData(news: News): Event
    fun toDomain(event: Event): News
}
