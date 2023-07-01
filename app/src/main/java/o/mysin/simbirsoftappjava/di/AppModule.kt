package o.mysin.simbirsoftappjava.di

import o.mysin.simbirsoftappjava.data.db.HelpCategoryRepository
import o.mysin.simbirsoftappjava.data.HelpCategoryRepositoryImpl
import o.mysin.simbirsoftappjava.data.db.NewsRepository
import o.mysin.simbirsoftappjava.data.NewsRepositoryImpl
import o.mysin.simbirsoftappjava.ui.filter.FilterViewModel
import o.mysin.simbirsoftappjava.ui.help.HelpViewModel
import o.mysin.simbirsoftappjava.ui.news.detail.NewsDetailViewModel
import o.mysin.simbirsoftappjava.ui.news.main.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    viewModel {
        HelpViewModel(get())
    }

    viewModel {
        FilterViewModel(get())
    }

    viewModel {
        NewsViewModel(get(), get())
    }

    viewModel {
        NewsDetailViewModel(get())
    }

    single<HelpCategoryRepository> {
        HelpCategoryRepositoryImpl(get(), get(named("helpCategories")))
    }

    single<NewsRepository> {
        NewsRepositoryImpl(get(), get(named("news")))
    }

}
