package ru.mys_ya.feature_news.di.component.news

import ru.mys_ya.feature_news.ui.news.main.NewsFragment
import ru.mys_ya.feature_news.ui.news.main.compose.NewsComposeFragment

interface NewsComponent {
    fun injectNewsFragment(newsFragment: NewsFragment)
    fun injectNewsFragment(newsFragment: NewsComposeFragment)
}
