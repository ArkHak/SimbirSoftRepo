package o.mysin.simbirsoftappjava.ui.search.main

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentSearchBinding
import o.mysin.simbirsoftappjava.ui.search.SearchFragmentsCommonViewModel
import o.mysin.simbirsoftappjava.utils.ZoomOutPageTransformer
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val commonViewModel: SearchFragmentsCommonViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commonViewModel.titleSearchView
            .observe(viewLifecycleOwner) { renderSearchView(it) }

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
        binding.searchView.setQuery("", false)
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

    @SuppressLint("CheckResult")
    private fun initSearchView() {

        val searchObservable = Observable.create { emitter ->
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    emitter.onNext(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    emitter.onNext(newText)
                    return true
                }
            })
        }

        searchObservable
            .debounce(SEARCH_TIMEOUT, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query ->
                if (query.isNotEmpty() && binding.searchTabLayout.selectedTabPosition == 0) {
                    commonViewModel.sendQueryToSearchEvents(query)
                }
            }
    }

    companion object {
        private const val SEARCH_TIMEOUT = 500L
    }

}
