package o.mysin.simbirsoftappjava.data.mapper

import o.mysin.simbirsoftappjava.data.db.entity.Event
import o.mysin.simbirsoftappjava.domain.mapper.EventMapper
import o.mysin.simbirsoftappjava.domain.model.News

class EventMapperImpl : EventMapper {

    override fun toEvent(news: News): Event =
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

    override fun fromEvent(event: Event): News =
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
