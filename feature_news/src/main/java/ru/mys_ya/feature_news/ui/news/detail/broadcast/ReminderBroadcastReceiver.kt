package ru.mys_ya.feature_news.ui.news.detail.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import ru.mys_ya.feature_news.ui.news.detail.worker.ReminderWorker
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_ID
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_NAME
import java.util.concurrent.TimeUnit

class ReminderBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ReminderWorker.ACTION_REMIND_LATER) {
            with(NotificationManagerCompat.from(context)) {
                cancel(1)
            }

            val eventId = intent.getIntExtra(EVENT_ID, 1)
            val eventName = intent.getStringExtra(EVENT_NAME)

            val workManager = WorkManager.getInstance(context)

            val inputData = workDataOf(
                EVENT_ID to eventId,
                EVENT_NAME to eventName,
            )
            val remindLaterWork = OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
                .setInputData(inputData)
                .build()

            workManager.enqueue(remindLaterWork)
        }
    }

    companion object {
        const val DEFAULT_TIMEOUT = 1L
    }

}


