package o.mysin.simbirsoftappjava.ui.news.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.datasource.local.NewsService
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.databinding.FragmentNewsBinding
import o.mysin.simbirsoftappjava.domain.model.NewsList
import o.mysin.simbirsoftappjava.ui.MainActivityViewModel
import o.mysin.simbirsoftappjava.utils.NewsItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding: FragmentNewsBinding by viewBinding()
    private val newsViewModel: NewsViewModel by viewModel()
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var adapter: NewsAdapter
    private var serviceStatus = ServiceStatus.INIT.value
    private lateinit var receiver: BroadcastReceiver

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            serviceStatus = savedInstanceState.getString(SERVICE_FLAG, ServiceStatus.INIT.value)
        }

        renderView()
        initAdapter()
        initRecycler()
        initActionButtons()
        initBroadcastReceiver()
        newsViewModel.newsList.observe(viewLifecycleOwner) { renderData(it) }
        updateData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SERVICE_FLAG, serviceStatus)
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    private fun renderView() {
        mainViewModel.setHideBottomNavigation(false)
    }

    private fun initAdapter() {
        adapter = NewsAdapter { idItem ->
            mainViewModel.setHideBottomNavigation(true)
            val action = NewsFragmentDirections.actionFragmentNewsToNewsDetailFragment(idItem)
            findNavController().navigate(action)
        }
    }

    private fun initRecycler() {
        binding.newsItemsRecyclerView.adapter = adapter

        binding.newsItemsRecyclerView.apply {
            addItemDecoration(NewsItemDecoration(binding.root.context))
        }
    }

    private fun initActionButtons() {
        binding.buttonFilters.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_news_to_filtersFragment)
        }
    }

    private fun initBroadcastReceiver() {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val newsListFromService: List<News> =
                        intent.getParcelableArrayListExtra(
                            NewsService.NEWS_LIST,
                            News::class.java
                        ) as ArrayList<News>
                    newsListFromService.let { newsViewModel.addListFromService(it) }
                } else {
                    val newsListFromService: NewsList? =
                        intent.getParcelableExtra(
                            NewsService.NEWS_LIST
                        )
                    newsListFromService?.let { newsViewModel.addListFromService(it.newsList) }
                }
                serviceStatus = ServiceStatus.SUCCESS.value
                newsViewModel.loadNews()
            }
        }
        registerReceiver(receiver)
    }


    private fun registerReceiver(receiver: BroadcastReceiver) {
        val filter = IntentFilter(NewsService.NEWS_SERVICE)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver, filter)
    }

    private fun unregisterReceiver(receiver: BroadcastReceiver) {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    private fun renderData(newsList: List<News>) {
        if (serviceStatus == ServiceStatus.SUCCESS.value) {
            showList(newsList.isEmpty())
            if (newsList.isNotEmpty()) {
                adapter.updateNewsList(newsList)
            }
        }
    }

    private fun updateData() {
        showLoadingData()
        if (serviceStatus == ServiceStatus.INIT.value) {
            startService()
            serviceStatus = ServiceStatus.LOADING.value
        }
        if (serviceStatus == ServiceStatus.SUCCESS.value) {
            newsViewModel.loadNews()
        }
    }

    private fun startService() {
        startNewsService()
    }

    private fun startNewsService() {
        val intent = Intent(context, NewsService::class.java)
        context?.startService(intent)
    }

    private fun showList(listIsEmpty: Boolean) {
        hideLoadingData()
        if (listIsEmpty) {
            binding.newsItemsRecyclerView.visibility = View.GONE
            binding.emptyListText.visibility = View.VISIBLE
        } else {
            binding.newsItemsRecyclerView.visibility = View.VISIBLE
            binding.emptyListText.visibility = View.GONE
        }
    }

    private fun showLoadingData() {
        binding.loadingProgressBar.visibility = View.VISIBLE
        binding.newsItemsRecyclerView.visibility = View.GONE
        binding.emptyListText.visibility = View.GONE
    }

    private fun hideLoadingData() {
        binding.loadingProgressBar.visibility = View.GONE
    }

    private enum class ServiceStatus(val value: String) {
        INIT(SERVICE_STATUS_INIT),
        LOADING(SERVICE_STATUS_LOADING),
        SUCCESS(SERVICE_STATUS_SUCCESS)
    }

    companion object {
        private const val SERVICE_FLAG = "SERVICE_FLAG"
        private const val SERVICE_STATUS_INIT = "SERVICE_STATUS_INIT"
        private const val SERVICE_STATUS_LOADING = "SERVICE_STATUS_LOADING"
        private const val SERVICE_STATUS_SUCCESS = "SERVICE_STATUS_SUCCESS"
    }
}
