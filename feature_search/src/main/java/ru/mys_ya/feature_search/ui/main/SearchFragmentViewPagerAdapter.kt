package ru.mys_ya.feature_search.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.mys_ya.feature_search.ui.events.SearchEventsFragment
import ru.mys_ya.feature_search.ui.nko.SearchNKOFragment

class SearchFragmentViewPagerAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return SearchTypeNumber.values().size
    }

    override fun createFragment(type: Int): Fragment {
        return when (type) {
            SearchTypeNumber.EVENTS.ordinal -> return SearchEventsFragment()
            SearchTypeNumber.NKO.ordinal -> return SearchNKOFragment()
            else -> SearchEventsFragment()
        }
    }
}
