package ru.mys_ya.core.domain.mapper

import ru.mys_ya.core.data.db.entity.Event
import ru.mys_ya.core.domain.model.News

interface EventMapper {
    fun toData(news: News): Event
    fun toDomain(event: Event): News
}
