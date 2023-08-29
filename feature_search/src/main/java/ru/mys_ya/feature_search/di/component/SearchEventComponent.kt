package ru.mys_ya.feature_search.di.component

import ru.mys_ya.feature_search.ui.events.SearchEventsFragment

fun interface SearchEventComponent {
    fun injectSearchEventFragment(searchEventFragment: SearchEventsFragment)
}
