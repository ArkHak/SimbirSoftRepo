package ru.mys_ya.feature_news.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.feature_news.mapper.EventMapperImpl
import ru.mys_ya.feature_news_api.mapper.EventMapper

@Module
class NewsMapperModule {
    @Provides
    fun provideEventMapper(): EventMapper = EventMapperImpl()

}
