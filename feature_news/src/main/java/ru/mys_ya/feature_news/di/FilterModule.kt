package ru.mys_ya.feature_news.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mys_ya.core.di.viewmodule.ViewModelKey
import ru.mys_ya.feature_news.ui.filter.FilterViewModel

@Module
fun interface FilterModule {
    @Binds
    @[IntoMap ViewModelKey(FilterViewModel::class)]
    fun bindFilterViewModel(viewModel: FilterViewModel): ViewModel
}
