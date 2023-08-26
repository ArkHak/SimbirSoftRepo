package ru.mys_ya.core.di.module

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.data.mapper.CategoryMapperImpl
import ru.mys_ya.core.data.mapper.EventMapperImpl
import ru.mys_ya.core.domain.mapper.CategoryMapper
import ru.mys_ya.core.domain.mapper.EventMapper

@Module
class MapperModule {

    @Provides
    fun provideCategoryMapper(): CategoryMapper {
        return CategoryMapperImpl()
    }

    @Provides
    fun provideEventMapper(): EventMapper {
        return EventMapperImpl()
    }
}
