package ru.mys_ya.feature_news_api.data

import ru.mys_ya.feature_news_api.domain.News

fun interface AssetManagerNews {

    fun getNewsListFromAsset(fileName: String): List<News>
}
