package ru.mys_ya.simbirsoftappjava.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.ActivityMainBinding
import ru.mys_ya.core.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val mainViewModel: MainActivityViewModel by viewModels()
    private val bottomNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationPanel }
    private val bottomNavigationPanelController by lazy { findNavController(R.id.navigation_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationPanel.setupWithNavController(bottomNavigationPanelController)

        initBadgeNews()

        mainViewModel.hideBottomNavigation.observe(this) { hide ->
            binding.bottomNavigationPanel.visibility = if (hide) View.GONE else View.VISIBLE
        }

//        if (intent.hasExtra(EVENT_ID)) {
//            val eventId = intent.getIntExtra(EVENT_ID, 1)
//            bottomNavigationPanelController.navigate("app://ss.com/newsDetail/$eventId")
//            findNavController().navigate(
//                ru.mys_ya.feature_news.R.id.news_detail_fragment, bundleOf(
//                    "newsId" to eventId
//                )
//            )
//        }
    }

    private fun initBadgeNews() {
        val badgeNews = bottomNavigationPanel.getOrCreateBadge(R.id.news_graph)
        lifecycleScope.launch(Dispatchers.IO) {
            mainViewModel.badgeFlow.collect { count ->
                badgeNews.number = count
                badgeNews.isVisible = count > 0
            }
        }
    }
}
