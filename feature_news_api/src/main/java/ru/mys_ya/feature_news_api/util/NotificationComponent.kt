package ru.mys_ya.feature_news_api.util

fun interface NotificationComponent {
    fun makeStatusNotification(eventId: Int, eventName: String, amount: Int)
}
