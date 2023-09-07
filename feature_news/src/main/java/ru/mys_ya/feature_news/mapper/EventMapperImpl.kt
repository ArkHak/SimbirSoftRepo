package ru.mys_ya.feature_news.mapper

import ru.mys_ya.core.data.db.entity.Event
import ru.mys_ya.feature_news_api.mapper.EventMapper
import ru.mys_ya.feature_news_api.data.News

class EventMapperImpl : EventMapper {

    override fun toData(news: News): Event =
        Event(
            id = news.id,
            organisation = news.organisation,
            address = news.address,
            phone = news.phone,
            photos = news.photos,
            name = news.name,
            description = news.description,
            fullDescription = news.fullDescription,
            startDate = news.startDate,
            endDate = news.endDate,
            categories = news.categories
        )

    override fun toDomain(event: Event): News =
        News(
            id = event.id,
            organisation = event.organisation,
            address = event.address,
            phone = event.phone,
            photos = event.photos,
            name = event.name,
            description = event.description,
            fullDescription = event.fullDescription,
            startDate = event.startDate,
            endDate = event.endDate,
            categories = event.categories
        )
}
