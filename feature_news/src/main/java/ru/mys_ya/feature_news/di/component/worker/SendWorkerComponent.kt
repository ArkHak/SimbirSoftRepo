package ru.mys_ya.feature_news.di.component.worker

import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker

fun interface SendWorkerComponent {
    fun injectSendWorker(sendWorker: SendWorker)
}
