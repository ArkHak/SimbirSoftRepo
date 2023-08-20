package o.mysin.simbirsoftappjava.domain.mapper

import o.mysin.simbirsoftappjava.data.db.entity.Event
import o.mysin.simbirsoftappjava.domain.model.News

interface EventMapper {
    fun toData(news: News): Event
    fun toDomain(event: Event): News
}
