package ru.mys_ya.core.di.module

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ru.mys_ya.core.domain.utils.AssetManager
import ru.mys_ya.core.utils.AssetManagerImpl

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
