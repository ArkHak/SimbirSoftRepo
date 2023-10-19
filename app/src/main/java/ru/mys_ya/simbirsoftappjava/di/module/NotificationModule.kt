package ru.mys_ya.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import ru.mys_ya.simbirsoftappjava.util.NotificationComponentImpl
import ru.mys_ya.feature_news_api.util.NotificationComponent
import javax.inject.Singleton

@Module
class NotificationModule {
    @Provides
    @Singleton
    fun provideNotificationComponent(): NotificationComponent {
        return NotificationComponentImpl()
    }
}

