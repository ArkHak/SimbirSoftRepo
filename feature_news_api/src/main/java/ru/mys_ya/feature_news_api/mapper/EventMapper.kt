package ru.mys_ya.feature_news_api.mapper

import ru.mys_ya.core.data.db.entity.Event
import ru.mys_ya.feature_news_api.data.News

interface EventMapper {
    fun toData(news: News): Event
    fun toDomain(event: Event): News
}
