package o.mysin.simbirsoftappjava.di

import o.mysin.simbirsoftappjava.domain.utils.AssetManager
import o.mysin.simbirsoftappjava.utils.AssetManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val assetManagerModule = module {

    single<AssetManager> {
        AssetManagerImpl(get(), androidContext())
    }
}
