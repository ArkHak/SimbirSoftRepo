package ru.mys_ya.feature_help.di

import dagger.Module
import dagger.Provides
import ru.mys_ya.feature_help.mapper.CategoryMapperImpl
import ru.mys_ya.feature_help_api.mapper.CategoryMapper

@Module
object HelpMapperModule {
    @Provides
    fun provideCategoryMapper(): CategoryMapper {
        return CategoryMapperImpl()
    }
}
