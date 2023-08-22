package o.mysin.simbirsoftappjava.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import o.mysin.simbirsoftappjava.data.db.dao.CategoryDao
import o.mysin.simbirsoftappjava.data.db.dao.EventDao
import o.mysin.simbirsoftappjava.data.db.database.AppDatabase
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
