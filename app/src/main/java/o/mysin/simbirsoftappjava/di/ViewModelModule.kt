package o.mysin.simbirsoftappjava.di

import o.mysin.simbirsoftappjava.ui.filter.FilterViewModel
import o.mysin.simbirsoftappjava.ui.help.HelpViewModel
import o.mysin.simbirsoftappjava.ui.news.detail.NewsDetailViewModel
import o.mysin.simbirsoftappjava.ui.news.main.NewsViewModel
import o.mysin.simbirsoftappjava.ui.search.events.SearchEventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
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

    viewModel {
        SearchEventsViewModel(get())
    }

}
