package o.mysin.simbirsoftappjava.domain.mapper

import o.mysin.simbirsoftappjava.data.db.entity.Event
import o.mysin.simbirsoftappjava.domain.model.News

interface EventMapper {
    fun toEvent(news: News): Event
    fun fromEvent(event: Event): News
}
