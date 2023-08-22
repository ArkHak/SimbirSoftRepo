package o.mysin.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import o.mysin.simbirsoftappjava.data.mapper.CategoryMapperImpl
import o.mysin.simbirsoftappjava.data.mapper.EventMapperImpl
import o.mysin.simbirsoftappjava.domain.mapper.CategoryMapper
import o.mysin.simbirsoftappjava.domain.mapper.EventMapper

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
