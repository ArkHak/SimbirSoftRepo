package o.mysin.simbirsoftappjava.di

import o.mysin.simbirsoftappjava.data.db.database.AppDatabase
import o.mysin.simbirsoftappjava.data.mapper.CategoryMapperImpl
import o.mysin.simbirsoftappjava.data.mapper.EventMapperImpl
import o.mysin.simbirsoftappjava.domain.mapper.CategoryMapper
import o.mysin.simbirsoftappjava.domain.mapper.EventMapper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    factory {
        AppDatabase.getDatabase(androidApplication()).categoryDao()
    }

    factory {
        AppDatabase.getDatabase(androidApplication()).eventDao()
    }

    factory<CategoryMapper> {
        CategoryMapperImpl()
    }

    factory<EventMapper> {
        EventMapperImpl()
    }

}
