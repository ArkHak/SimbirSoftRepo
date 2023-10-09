package ru.mys_ya.feature_news.ui.news.detail.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import ru.mys_ya.core.R
import ru.mys_ya.feature_news.ui.news.detail.worker.Constants.CHANNEL_ID
import ru.mys_ya.feature_news.ui.news.detail.worker.Constants.NOTIFICATION_ID
import ru.mys_ya.feature_news.ui.news.detail.worker.Constants.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import ru.mys_ya.feature_news.ui.news.detail.worker.Constants.VERBOSE_NOTIFICATION_CHANNEL_NAME

fun makeStatusNotification(eventName: String, amount: Int, context: Context) {
    val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
    val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(CHANNEL_ID, name, importance)
    channel.description = description

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManager.createNotificationChannel(channel)

    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(eventName)
        .setContentText("Спасибо, что пожертвовали $amount₽! Будем очень признательны, если вы сможете пожертвовать еще больше.")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))
        .build()

    notificationManager.notify(NOTIFICATION_ID, notification)
}

object Constants {
    @JvmField
    val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
        "Verbose WorkManager Notifications"
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"

    const val CHANNEL_ID = "CHANNEL_ID"
    const val NOTIFICATION_ID = 1
}
