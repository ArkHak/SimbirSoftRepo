package ru.mys_ya.core.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/** (3)
 * Учим даггер, создавать фабрику вьюмоделей при помощи мапы Map<Class<out ViewModel>, Provider<ViewModel>>
 *
 * Фабрика вьюмоделей, которая создает их при помощи даггера
 */
class MultiViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelFactories.getValue(modelClass as Class<ViewModel>).get() as T
    }
}