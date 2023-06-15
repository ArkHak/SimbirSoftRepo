package o.mysin.simbirsoftappjava.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private val bottomNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationPanel }

    private val bottomNavigationPanelController by lazy { findNavController(R.id.navigation_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationPanel.setupWithNavController(bottomNavigationPanelController)
    }
}
