package o.mysin.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import o.mysin.simbirsoftappjava.data.db.dao.CategoryDao
import o.mysin.simbirsoftappjava.data.db.dao.EventDao
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.data.repository.HelpCategoryRepositoryImpl
import o.mysin.simbirsoftappjava.data.repository.NewsRepositoryImpl
import o.mysin.simbirsoftappjava.domain.mapper.CategoryMapper
import o.mysin.simbirsoftappjava.domain.mapper.EventMapper
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.utils.AssetManager
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
