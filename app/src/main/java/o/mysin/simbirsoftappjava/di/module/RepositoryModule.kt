package o.mysin.simbirsoftappjava.di.module

import dagger.Module
import dagger.Provides
import ru.mys_ya.core.data.db.dao.CategoryDao
import ru.mys_ya.core.data.db.dao.EventDao
import ru.mys_ya.network.ApiService
import ru.mys_ya.feature_news.data.NewsRepositoryImpl
import ru.mys_ya.feature_help_api.mapper.CategoryMapper
import ru.mys_ya.feature_news_api.mapper.EventMapper
import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.feature_news_api.repository.NewsRepository
import ru.mys_ya.feature_help.data.HelpCategoryRepositoryImpl
import ru.mys_ya.feature_help_api.data.local.AssetManagerHelp
import ru.mys_ya.feature_news_api.data.AssetManagerNews
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHelpCategoryRepository(
        assetManager: AssetManagerHelp,
        apiService: ApiService,
        categoryDao: CategoryDao,
        mapper: CategoryMapper,
    ): HelpCategoryRepository {
        return HelpCategoryRepositoryImpl(
            assetManager,
            apiService,
            categoryDao,
            mapper
        )
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        assetManager: AssetManagerNews,
        apiService: ApiService,
        eventDao: EventDao,
        mapper: EventMapper,
    ): NewsRepository {
        return NewsRepositoryImpl(
            assetManager,
            apiService,
            eventDao,
            mapper
        )
    }

}
