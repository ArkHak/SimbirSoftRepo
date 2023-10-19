package ru.mys_ya.feature_news_api.util

import android.content.Context
import ru.mys_ya.core.utils.TypeNotification

fun interface NotificationComponent {
    fun makeStatusNotification(
        context: Context,
        eventId: Int,
        eventName: String,
        amount: Int,
        typeNotification: TypeNotification,
    )
}
