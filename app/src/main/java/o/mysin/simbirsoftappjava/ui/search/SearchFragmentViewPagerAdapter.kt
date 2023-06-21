package o.mysin.simbirsoftappjava.ui.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchFragmentViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return SearchTypeNumber.values().size
    }

    override fun createFragment(type: Int): Fragment {
        return when (type) {
            SearchTypeNumber.EVENTS.number -> return SearchByEventsFragment()
            SearchTypeNumber.NKO.number -> return SearchByNKOFragment()
            else -> SearchByEventsFragment()
        }
    }
}

enum class SearchTypeNumber(val number: Int) {
    EVENTS(0),
    NKO(1)
}
