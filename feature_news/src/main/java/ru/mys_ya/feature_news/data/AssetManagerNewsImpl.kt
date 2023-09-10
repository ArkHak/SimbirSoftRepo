package ru.mys_ya.feature_news.data

import android.content.Context
import com.google.gson.Gson
import ru.mys_ya.feature_news_api.data.AssetManagerNews
import ru.mys_ya.feature_news_api.domain.News
import java.io.InputStreamReader

class AssetManagerNewsImpl(
    private val gson: Gson,
    private val context: Context,
) : AssetManagerNews {
    override fun getNewsListFromAsset(fileName: String): List<News> {
        return context.assets.open(fileName).use { inputStream ->
            InputStreamReader(inputStream).use { reader ->
                gson.fromJson(reader, Array<News>::class.java)
                    .toList()
            }
        }
    }
}
