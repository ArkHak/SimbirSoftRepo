package ru.mys_ya.feature_news.di.component.worker

import ru.mys_ya.feature_news.ui.news.detail.worker.ReminderWorker
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker

interface WorkerComponent {
    fun injectSendWorker(sendWorker: SendWorker)
    fun injectReminderWorker(reminderWorker: ReminderWorker)
}
