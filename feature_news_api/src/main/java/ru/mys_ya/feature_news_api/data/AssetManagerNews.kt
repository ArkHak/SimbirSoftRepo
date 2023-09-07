package ru.mys_ya.feature_news_api.data

fun interface AssetManagerNews {

    fun getNewsListFromAsset(fileName: String): List<News>
}
