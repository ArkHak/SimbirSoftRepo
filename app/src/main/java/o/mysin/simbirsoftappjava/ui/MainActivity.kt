package o.mysin.simbirsoftappjava.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private val mainViewModel: MainActivityViewModel by viewModels()

    private val bottomNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationPanel }

    private val bottomNavigationPanelController by lazy { findNavController(R.id.navigation_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationPanel.setupWithNavController(bottomNavigationPanelController)

        mainViewModel.hideBottomNavigation.observe(this) { hide ->
            binding.bottomNavigationPanel.visibility = if (hide) View.GONE else View.VISIBLE
        }

    }
}
