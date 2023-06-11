package o.mysin.simbirsoftappjava

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import o.mysin.simbirsoftappjava.ui.MainActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, SPLASH_SCREEN_TIMEOUT)
    }

    companion object {
        private const val SPLASH_SCREEN_TIMEOUT = 2000L
    }
}