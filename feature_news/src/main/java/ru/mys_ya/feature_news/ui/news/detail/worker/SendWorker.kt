package ru.mys_ya.feature_news.ui.news.detail.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SendWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    private val appContext = applicationContext
    override fun doWork(): Result {

        val eventId = inputData.getInt(EVENT_ID, 0)
        val eventName = inputData.getString(EVENT_NAME)
        val amount = inputData.getInt(EVENT_AMOUNT, 0)

        makeStatusNotification(eventName = eventName ?: "", amount = amount, appContext)

        return try {
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }

    companion object {
        const val EVENT_ID = "EVENT_ID"
        const val EVENT_NAME = "EVENT_NAME"
        const val EVENT_AMOUNT = "EVENT_AMOUNT"
    }
}
