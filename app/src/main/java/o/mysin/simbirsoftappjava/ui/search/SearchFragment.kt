package o.mysin.simbirsoftappjava.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentSearchBinding
import o.mysin.simbirsoftappjava.utils.ZoomOutPageTransformer

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        requireActivity().apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            binding.searchView.apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                setIconifiedByDefault(false)
            }
        }

        initViewPager()
    }

    private fun initViewPager() {
        val adapter = SearchFragmentViewPagerAdapter(this)
        binding.searchFragmentViewPager.adapter = adapter
        binding.searchFragmentViewPager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(binding.searchTabLayout, binding.searchFragmentViewPager) { tab, type ->
            tab.text = tabNames(type)
        }.attach()
    }

    private fun tabNames(type: Int): String? {
        when (type) {
            SearchTypeNumber.EVENTS.number -> return getString(R.string.tab_name_search_by_events)
            SearchTypeNumber.NKO.number -> return getString(R.string.tab_name_search_by_nko)
        }
        return null
    }
}
