package ru.mys_ya.simbirsoftappjava.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import ru.mys_ya.core.R
import ru.mys_ya.feature_news_api.util.NotificationComponent

class NotificationComponentImpl(
    private val context: Context,
) : NotificationComponent {
    override fun makeStatusNotification(eventId: Int, eventName: String, amount: Int) {
        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)

//        val intent = Intent(context, MainActivity::class.java)
//        intent.putExtra(EVENT_ID, eventId)
//        intent.apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent = PendingIntent.getActivity(
//            context, 0, intent,
//            PendingIntent.FLAG_IMMUTABLE
//        )

        val args = bundleOf("newsId" to eventId)
        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(o.mysin.simbirsoftappjava.R.navigation.navigation_graph)
            .setDestination(ru.mys_ya.feature_news.R.id.news_detail_fragment)
            .setArguments(args)
            .createPendingIntent()

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(eventName)
            .setContentText("Спасибо, что пожертвовали $amount₽! ...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Спасибо, что пожертвовали $amount₽! Будем очень признательны, если вы сможете пожертвовать еще больше.")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setVibrate(LongArray(0))
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
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
