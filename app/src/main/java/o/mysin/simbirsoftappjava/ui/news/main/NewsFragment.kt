package o.mysin.simbirsoftappjava.ui.news.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.entity.News
import o.mysin.simbirsoftappjava.databinding.FragmentNewsBinding
import o.mysin.simbirsoftappjava.ui.MainActivityViewModel
import o.mysin.simbirsoftappjava.utils.MarginDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding: FragmentNewsBinding by viewBinding()
    private val newsViewModel: NewsViewModel by viewModel()
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val adapter: NewsAdapter by lazy { NewsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel.newsList.observe(viewLifecycleOwner) { renderData(it) }

        initAdapter()
        initRecycler()
        initActionButtons()
    }

    override fun onResume() {
        super.onResume()
        newsViewModel.loadNews()
        mainViewModel.setHideBottomNavigation(false)
    }

    private fun initAdapter() {
        adapter.setOnItemClickListener { idItem ->
            mainViewModel.setHideBottomNavigation(true)
            val action = NewsFragmentDirections.actionFragmentNewsToNewsDetailFragment(idItem)
            findNavController().navigate(action)
        }
    }

    private fun renderData(newsList: List<News>) {
        adapter.updateNewsList(newsList)
    }

    private fun initRecycler() {
        binding.newsItemsRecyclerView.adapter = adapter

        binding.newsItemsRecyclerView.apply {
            addItemDecoration(MarginDecoration(binding.root.context))
        }
    }

    private fun initActionButtons() {
        binding.buttonFilters.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_news_to_filtersFragment)
        }
    }

}
