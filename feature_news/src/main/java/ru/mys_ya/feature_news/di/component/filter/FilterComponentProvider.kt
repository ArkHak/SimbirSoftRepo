package ru.mys_ya.feature_news.di.component.filter

fun interface FilterComponentProvider {
    fun getFilterComponent(): FilterComponent
}
