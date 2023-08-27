package ru.mys_ya.feature_news.component.news

fun interface NewsComponentProvider {
    fun getNewsComponent(): NewsComponent
}
