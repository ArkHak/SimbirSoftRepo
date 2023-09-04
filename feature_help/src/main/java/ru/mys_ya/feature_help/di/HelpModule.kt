package ru.mys_ya.feature_help.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mys_ya.core.di.viewmodule.ViewModelKey
import ru.mys_ya.feature_help.ui.HelpViewModel

@Module
fun interface HelpModule {
    @Binds
    @[IntoMap ViewModelKey(HelpViewModel::class)]
    fun bindHelpViewModel(viewModel: HelpViewModel): ViewModel
}
