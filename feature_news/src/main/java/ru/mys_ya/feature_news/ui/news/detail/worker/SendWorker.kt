package ru.mys_ya.feature_news.ui.news.detail.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import ru.mys_ya.feature_news.di.component.worker.SendWorkerComponentProvider
import ru.mys_ya.feature_news_api.util.NotificationComponent
import javax.inject.Inject

class SendWorker @Inject constructor(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    @Inject
    lateinit var notificationComponent: NotificationComponent

    init {
        (applicationContext as SendWorkerComponentProvider)
            .getSendWorkerComponent()
            .injectSendWorker(this)
    }

    override fun doWork(): Result {

        val eventId = inputData.getInt(EVENT_ID, 0)
        val eventName = inputData.getString(EVENT_NAME)
        val amount = inputData.getInt(EVENT_AMOUNT, 0)

        notificationComponent.makeStatusNotification(
            eventId = eventId,
            eventName = eventName ?: "",
            amount = amount
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
