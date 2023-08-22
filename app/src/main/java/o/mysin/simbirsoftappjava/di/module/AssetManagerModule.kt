package o.mysin.simbirsoftappjava.di.module

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import o.mysin.simbirsoftappjava.domain.utils.AssetManager
import o.mysin.simbirsoftappjava.utils.AssetManagerImpl

@Module
class AssetManagerModule {

    @Provides
    fun provideAssetManager(
        gson: Gson,
        context: Context,
    ): AssetManager {
        return AssetManagerImpl(gson, context)
    }
}
