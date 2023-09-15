package ru.mys_ya.feature_news.ui.news.main.compose

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.mys_ya.core.MainActivityViewModel
import ru.mys_ya.core.di.viewmodule.MultiViewModelFactory
import ru.mys_ya.feature_news.R
import ru.mys_ya.feature_news.di.component.news.NewsComponentProvider
import ru.mys_ya.feature_news.ui.news.main.NewsViewModel
import ru.mys_ya.feature_news_api.domain.News
import javax.inject.Inject

class NewsComposeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory
    private val newsViewModel by viewModels<NewsViewModel> {
        viewModelFactory
    }

    private val mainViewModel: MainActivityViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as NewsComponentProvider)
            .getNewsComponent()
            .injectNewsFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    NewsScreen(
                        viewModel = newsViewModel,
                        clickFilter = {
                            navigateFilterScreen()
                        },
                        clickItem = {
                            navigateNewsDetail(it)
                        }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newsList.observe(viewLifecycleOwner) { renderData(it) }
        renderBottomButton()
    }

    private fun renderData(newsList: List<News>) {
        setBadgeCount(newsList)
    }

    private fun navigateFilterScreen() {
        findNavController().navigate(R.id.action_fragment_news_to_filtersFragment)
    }

    private fun navigateNewsDetail(idItem: Int) {
        newsViewModel.setIsViewedNews(idItem)
        mainViewModel.setHideBottomNavigation(true)
        val action = NewsComposeFragmentDirections.actionFragmentNewsToNewsDetailFragment(idItem)
        findNavController().navigate(action)
    }

    private fun setBadgeCount(newsList: List<News>) {
        val currentCountNewsNotViewed = newsViewModel.getCountNewsNotViewed(newsList)
        mainViewModel.setBadgeCount(currentCountNewsNotViewed)
    }

    private fun renderBottomButton() {
        mainViewModel.setHideBottomNavigation(false)
    }

}
