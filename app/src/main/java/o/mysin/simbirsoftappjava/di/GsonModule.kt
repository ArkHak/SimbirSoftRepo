package o.mysin.simbirsoftappjava.di

import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val gsonModule = module {

    single(named("helpCategories")) {
        androidContext().assets.open("categories.json")
    }

    single(named("news")) {
        androidContext().assets.open("news.json")
    }

    single {
        Gson()
    }
}
