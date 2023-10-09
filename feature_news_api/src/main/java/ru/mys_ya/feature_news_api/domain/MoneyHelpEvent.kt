package ru.mys_ya.feature_news_api.domain

data class MoneyHelpEvent(
    val eventId: Int,
    val eventName: String,
    val eventAmount: Int,
)
