package ru.mys_ya.feature_news.di.component.detail

import ru.mys_ya.feature_news.ui.news.detail.NewsDetailFragment

fun interface NewsDetailComponent {
    fun injectNewsDetailFragment(newsDetailFragment: NewsDetailFragment)
}
