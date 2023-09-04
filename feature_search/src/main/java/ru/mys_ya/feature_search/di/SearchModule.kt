package ru.mys_ya.feature_search.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mys_ya.core.di.viewmodule.ViewModelKey
import ru.mys_ya.feature_search.ui.events.SearchEventsViewModel

@Module
fun interface SearchModule {

    @Binds
    @[IntoMap ViewModelKey(SearchEventsViewModel::class)]
    fun bindSearchEventsViewModel(viewModel: SearchEventsViewModel): ViewModel
}
