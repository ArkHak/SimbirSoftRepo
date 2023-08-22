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
        context: Context,
    ): CategoryDao {
        return AppDatabase.getDatabase(context).categoryDao()
    }

    @Provides
    @Singleton
    fun provideEventDao(
        context: Context,
    ): EventDao {
        return AppDatabase.getDatabase(context).eventDao()
    }

}
