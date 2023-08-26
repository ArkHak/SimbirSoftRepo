package ru.mys_ya.core.di.module

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.data.db.dao.CategoryDao
import ru.mys_ya.core.data.db.dao.EventDao
import ru.mys_ya.core.data.network.ApiService
import ru.mys_ya.core.data.repository.HelpCategoryRepositoryImpl
import ru.mys_ya.core.data.repository.NewsRepositoryImpl
import ru.mys_ya.core.domain.mapper.CategoryMapper
import ru.mys_ya.core.domain.mapper.EventMapper
import ru.mys_ya.core.domain.repository.HelpCategoryRepository
import ru.mys_ya.core.domain.repository.NewsRepository
import ru.mys_ya.core.domain.utils.AssetManager
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHelpCategoryRepository(
        assetManager: AssetManager,
        apiService: ApiService,
        categoryDao: CategoryDao,
        mapper: CategoryMapper,
    ): HelpCategoryRepository {
        return HelpCategoryRepositoryImpl(assetManager, apiService, categoryDao, mapper)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        assetManager: AssetManager,
        apiService: ApiService,
        eventDao: EventDao,
        mapper: EventMapper,
    ): NewsRepository {
        return NewsRepositoryImpl(assetManager, apiService, eventDao, mapper)
    }

}
