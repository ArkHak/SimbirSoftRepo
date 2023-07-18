package o.mysin.simbirsoftappjava.data.datasource.local

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.model.NewsList
import java.io.InputStreamReader
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NewsService : Service() {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        executorService.execute {
            val inputStream = application.assets.open("news.json")
            val reader = InputStreamReader(inputStream)
            try {
                Thread.sleep(TIMEOUT)
                val listNews = Gson().fromJson(reader, Array<News>::class.java).toList()
                sendResult(listNews)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            } finally {
                reader.close()
                inputStream.close()
            }
            stopSelf()
        }

        return START_NOT_STICKY
    }

    private fun sendResult(listNews: List<News>) {
        val intent = Intent(NEWS_SERVICE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.putParcelableArrayListExtra(NEWS_LIST, ArrayList(listNews))
        } else {
            val parcelNewsList = NewsList(listNews)
            intent.putExtra(NEWS_LIST, parcelNewsList)
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    //private fun sendResult(listNews: List<News>) {
    //         val intent = Intent(NEWS_SERVICE)
    //

    override fun onDestroy() {
        executorService.shutdown()
        super.onDestroy()
    }

    companion object {
        const val NEWS_SERVICE = "NEWS_SERVICE"
        const val NEWS_LIST = "NEWS_LIST"
        private const val TIMEOUT = 5000L
    }
}
