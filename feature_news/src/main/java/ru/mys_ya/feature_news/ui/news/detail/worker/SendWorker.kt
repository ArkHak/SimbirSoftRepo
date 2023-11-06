package ru.mys_ya.feature_news.ui.news.detail.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import ru.mys_ya.core.utils.TypeNotification
import ru.mys_ya.feature_news.di.component.worker.WorkerComponentProvider
import ru.mys_ya.feature_news_api.util.NotificationComponent
import javax.inject.Inject

class SendWorker @Inject constructor(val context: Context, params: WorkerParameters) :
    Worker(context, params) {

    @Inject
    lateinit var notificationComponent: NotificationComponent

    init {
        (applicationContext as WorkerComponentProvider)
            .getSendWorkerComponent()
            .injectSendWorker(this)
    }

    override fun doWork(): Result {

        val eventId = inputData.getInt(EVENT_ID, 1)
        val eventName = inputData.getString(EVENT_NAME)
        val amount = inputData.getInt(EVENT_AMOUNT, 0)

        notificationComponent.makeStatusNotification(
            context = context,
            eventId = eventId,
            eventName = eventName ?: "",
            amount = amount,
            typeNotification = TypeNotification.SEND_NOTIFICATION
        )

        return try {
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }

    companion object {
        const val EVENT_ID = "newsId"
        const val EVENT_NAME = "EVENT_NAME"
        const val EVENT_AMOUNT = "EVENT_AMOUNT"
    }
}
