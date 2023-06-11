package o.mysin.simbirsoftappjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private val buttonNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationPanel }

    private val navigationController by lazy { findNavController(R.id.navigation_fragment_container) }

    private val listener by lazy {
        NavController.OnDestinationChangedListener { _, destination, _ ->
            binding.labelFragmentTextVew.text = when (destination.id) {
                Routes.FRAGMENT_HELP.id -> resources.getString(R.string.title_help)
                Routes.FRAGMENT_HISTORY.id -> resources.getString(R.string.title_history)
                Routes.FRAGMENT_NEWS.id -> resources.getString(R.string.title_news)
                Routes.FRAGMENT_PROFILE.id -> resources.getString(R.string.title_profile)
                Routes.FRAGMENT_SEARCH.id -> resources.getString(R.string.title_search)
                else -> resources.getString(R.string.title_help)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonNavigationPanel.setupWithNavController(navigationController)
        navigationController.addOnDestinationChangedListener(listener)
    }
}

private enum class Routes(val id: Int) {
    FRAGMENT_HELP(R.id.fragment_help),
    FRAGMENT_HISTORY(R.id.fragment_history),
    FRAGMENT_NEWS(R.id.fragment_news),
    FRAGMENT_PROFILE(R.id.fragment_profile),
    FRAGMENT_SEARCH(R.id.fragment_search),
}
