package o.mysin.simbirsoftappjava.di

import o.mysin.simbirsoftappjava.data.network.RetrofitClient
import org.koin.dsl.module

val retrofitModule = module {
    single {
        RetrofitClient.create()
    }
}
