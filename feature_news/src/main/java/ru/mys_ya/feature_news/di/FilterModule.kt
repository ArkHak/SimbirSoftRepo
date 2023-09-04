package ru.mys_ya.feature_news.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mys_ya.core.di.viewModel.ViewModelKey
import ru.mys_ya.feature_news.ui.filter.FilterViewModel

@Module
interface BindFilterModule {

    @Binds
    /** (2)
     * Говорим даггеру, чтобы он создал внутри себя мапу, где ключом будет
     * KClass<out ViewModel>, а значением объект (Provider), который умеет создавать ViewModel.
     * +
     * Кладем в эту мапу объект, умеющий создавать FilterViewModel по ключу FilterViewModel::class
     */
    @[IntoMap ViewModelKey(FilterViewModel::class)]
    fun bindFilterViewModel(viewModel: FilterViewModel): ViewModel
}