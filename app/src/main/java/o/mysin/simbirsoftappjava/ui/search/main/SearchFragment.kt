package o.mysin.simbirsoftappjava.ui.search.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentSearchBinding
import o.mysin.simbirsoftappjava.ui.search.SearchFragmentsCommonViewModel
import ru.mys_ya.core.utils.ZoomOutPageTransformer

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val commonViewModel: SearchFragmentsCommonViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commonViewModel.titleSearchView
            .observe(viewLifecycleOwner) { renderSearchView(it) }

        searchViewModel.queryByEventScreen
            .observe(viewLifecycleOwner) { commonViewModel.sendQueryToSearchEvents(it) }

        requireActivity().apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            binding.searchView.apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                setIconifiedByDefault(false)
            }
        }

        initViewPager()
        initSearchView()
    }

    private fun renderSearchView(idTitle: Int) {
        binding.searchView.queryHint = requireContext().getString(idTitle)
        if (binding.searchTabLayout.selectedTabPosition == 0) {
            binding.searchView.setQuery(searchViewModel.queryByEventScreen.value, false)
        } else {
            binding.searchView.setQuery("", false)
        }
    }

    private fun initViewPager() {
        val adapter = SearchFragmentViewPagerAdapter(this)
        binding.searchFragmentViewPager.adapter = adapter
        binding.searchFragmentViewPager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(binding.searchTabLayout, binding.searchFragmentViewPager) { tab, type ->
            tab.text = tabNames(type)
        }.attach()
    }

    private fun tabNames(type: Int): String = getString(SearchTypeNumber.getTitleIdByIndex(type))

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel.setSearchQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchViewModel.setSearchQuery(newText)
                return true
            }
        })
    }

}
