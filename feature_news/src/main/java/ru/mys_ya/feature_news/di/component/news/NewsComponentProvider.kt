package ru.mys_ya.feature_news.di.component.news

fun interface NewsComponentProvider {
    fun getNewsComponent(): NewsComponent
}
