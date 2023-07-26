package o.mysin.simbirsoftappjava.utils

import android.content.Context
import com.google.gson.Gson
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.domain.utils.AssetManager
import java.io.InputStreamReader

class AssetManagerImpl(
    private val gson: Gson,
    private val context: Context,
) : AssetManager {
    override fun getNewsListFromAsset(fileName: String): List<News> {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        val listNews = gson.fromJson(reader, Array<News>::class.java).toList()
        reader.close()
        inputStream.close()
        return listNews
    }

    override fun getHelpCategoryListFromAsset(fileName: String): List<HelpCategory> {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        val helpCategory = gson.fromJson(reader, Array<HelpCategory>::class.java).toList()
        reader.close()
        inputStream.close()
        return helpCategory
    }
}

