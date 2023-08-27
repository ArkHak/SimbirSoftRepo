package ru.mys_ya.feature_news.component.filter

import ru.mys_ya.feature_news.ui.filter.FilterFragment

fun interface FilterComponent {

    fun injectFilterFragment(filterFragment: FilterFragment)

}
