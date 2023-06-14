package o.mysin.simbirsoftappjava

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import o.mysin.simbirsoftappjava.ui.MainActivity

class SplashFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        Handler().postDelayed({
            startActivity(Intent(this@SplashFragment, MainActivity::class.java))
            finish()
        }, SPLASH_SCREEN_TIMEOUT)
    }

    companion object {
        private const val SPLASH_SCREEN_TIMEOUT = 2000L
    }
}