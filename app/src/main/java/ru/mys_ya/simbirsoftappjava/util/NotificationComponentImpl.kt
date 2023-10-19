package ru.mys_ya.simbirsoftappjava.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import ru.mys_ya.core.R
import ru.mys_ya.core.utils.TypeNotification
import ru.mys_ya.feature_news.ui.news.detail.broadcast.ReminderBroadcastReceiver
import ru.mys_ya.feature_news.ui.news.detail.worker.ReminderWorker.Companion.ACTION_REMIND_LATER
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_ID
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_NAME
import ru.mys_ya.feature_news_api.util.NotificationComponent
import ru.mys_ya.simbirsoftappjava.ui.MainActivity

class NotificationComponentImpl : NotificationComponent {

    override fun makeStatusNotification(
        context: Context,
        eventId: Int,
        eventName: String,
        amount: Int,
        typeNotification: TypeNotification,
    ) {
        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)

        val args = bundleOf("newsId" to eventId)
        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(o.mysin.simbirsoftappjava.R.navigation.navigation_graph)
            .setDestination(ru.mys_ya.feature_news.R.id.news_detail_fragment)
            .setArguments(args)
            .setComponentName(MainActivity::class.java)
            .createPendingIntent()

        when (typeNotification) {
            TypeNotification.SEND_NOTIFICATION -> {
                fun createRemindPendingIntent(): PendingIntent {
                    val intent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
                        action = ACTION_REMIND_LATER
                        putExtra(EVENT_ID, eventId)
                        putExtra(EVENT_NAME, eventName)
                    }
                    return PendingIntent.getBroadcast(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                }

                val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_logo)
                    .setContentTitle(eventName)
                    .setContentText("Спасибо, что пожертвовали $amount₽! ...")
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText("Спасибо, что пожертвовали $amount₽! Будем очень признательны, если вы сможете пожертвовать еще больше.")
                    )
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .addAction(
                        R.drawable.ic_history,
                        "Напомнить позже",
                        createRemindPendingIntent()
                    )
                    .setVibrate(LongArray(0))
                    .build()

                notificationManager.notify(NOTIFICATION_ID, notification)
            }

            TypeNotification.REMINDER_NOTIFICATION -> {
                val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_logo)
                    .setContentText("Напоминаем, что ...")
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText("Напоминаем, что мы будем очень признательны, если вы сможете пожертвовать еще больше.")
                    )
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setVibrate(LongArray(0))
                    .build()

                notificationManager.notify(NOTIFICATION_ID, notification)
            }
        }
    }

    companion object {
        @JvmField
        val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
            "Verbose WorkManager Notifications"
        const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications whenever work starts"

        const val CHANNEL_ID = "CHANNEL_ID"
        const val NOTIFICATION_ID = 1
    }
}
