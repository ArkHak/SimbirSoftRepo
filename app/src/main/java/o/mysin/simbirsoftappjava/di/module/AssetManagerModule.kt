package o.mysin.simbirsoftappjava.di.module

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ru.mys_ya.feature_help.data.local.AssetManagerHelpImpl
import ru.mys_ya.feature_help_api.data.local.AssetManagerHelp
import ru.mys_ya.feature_news.data.AssetManagerNewsImpl
import ru.mys_ya.feature_news_api.data.AssetManagerNews

@Module
class AssetManagerModule {

    @Provides
    fun provideAssetManagerNews(
        gson: Gson,
        context: Context,
    ): AssetManagerNews {
        return AssetManagerNewsImpl(gson, context)
    }

    @Provides
    fun provideAssetManagerHelp(
        gson: Gson,
        context: Context,
    ): AssetManagerHelp {
        return AssetManagerHelpImpl(gson, context)
    }
}
