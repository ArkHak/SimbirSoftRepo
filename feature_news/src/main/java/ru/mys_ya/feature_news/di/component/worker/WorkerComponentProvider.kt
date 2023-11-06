package ru.mys_ya.feature_news.di.component.worker

interface WorkerComponentProvider {
    fun getSendWorkerComponent(): WorkerComponent
    fun getReminderWorkerComponent(): WorkerComponent
}
