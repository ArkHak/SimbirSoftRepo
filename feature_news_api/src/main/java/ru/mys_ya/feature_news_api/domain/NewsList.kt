package ru.mys_ya.feature_news_api.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsList(
    val newsList: List<News>,
) : Parcelable
