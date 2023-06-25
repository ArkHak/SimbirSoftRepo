package o.mysin.simbirsoftappjava.ui.search.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import o.mysin.simbirsoftappjava.ui.search.events.SearchEventsFragment
import o.mysin.simbirsoftappjava.ui.search.nko.SearchNKOFragment

class SearchFragmentViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
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
