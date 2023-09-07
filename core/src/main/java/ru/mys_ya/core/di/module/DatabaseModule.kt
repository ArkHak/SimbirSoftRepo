package ru.mys_ya.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mys_ya.core.database.dao.CategoryDao
import ru.mys_ya.core.database.dao.EventDao
import ru.mys_ya.core.database.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCategoryDao(
        appDatabase: AppDatabase
    ): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideEventDao(
        appDatabase: AppDatabase
    ): EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context,
    ): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

}
