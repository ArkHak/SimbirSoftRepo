package ru.mys_ya.feature_news.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mys_ya.core.di.viewmodule.ViewModelKey
import ru.mys_ya.feature_news.ui.news.detail.NewsDetailViewModel
import ru.mys_ya.feature_news.ui.news.main.NewsViewModel

@Module
interface NewsViewModelModule {

    @Binds
    @[IntoMap ViewModelKey(NewsViewModel::class)]
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(NewsDetailViewModel::class)]
    fun bindNewsDetailViewModel(viewModel: NewsDetailViewModel): ViewModel
}
