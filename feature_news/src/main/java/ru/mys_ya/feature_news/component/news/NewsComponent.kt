package ru.mys_ya.feature_news.component.news

import ru.mys_ya.feature_news.ui.news.main.NewsFragment

fun interface NewsComponent {
        fun injectNewsFragment(newsFragment: NewsFragment)
}
