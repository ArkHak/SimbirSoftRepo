package ru.mys_ya.feature_news.ui.news.detail.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import ru.mys_ya.core.utils.TypeNotification
import ru.mys_ya.feature_news.di.component.worker.WorkerComponentProvider
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_ID
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_NAME
import ru.mys_ya.feature_news_api.util.NotificationComponent
import javax.inject.Inject

class ReminderWorker @Inject constructor(val context: Context, params: WorkerParameters) :
    Worker(context, params) {

    @Inject
    lateinit var notificationComponent: NotificationComponent

    init {
        (applicationContext as WorkerComponentProvider)
            .getReminderWorkerComponent()
            .injectReminderWorker(this)
    }

    override fun doWork(): Result {

        val eventId = inputData.getInt(EVENT_ID, 1)
        val eventName = inputData.getString(EVENT_NAME)

        notificationComponent.makeStatusNotification(
            context = context,
            eventId = eventId,
            eventName = eventName ?: "",
            amount = 0,
            typeNotification = TypeNotification.REMINDER_NOTIFICATION
        )

        return try {
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }

    companion object {
        const val ACTION_REMIND_LATER = "ACTION_REMIND_LATER"
    }

}
