package o.mysin.simbirsoftappjava.di

import com.google.gson.Gson
import org.koin.dsl.module

val gsonModule = module {

    single {
        Gson()
    }
}
