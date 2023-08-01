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
        return context.assets.open(fileName).use { inputStream ->
            InputStreamReader(inputStream).use { reader ->
                gson.fromJson(reader, Array<News>::class.java).toList()
            }
        }
    }

    override fun getHelpCategoryListFromAsset(fileName: String): List<HelpCategory> {
        return context.assets.open(fileName).use { inputStream ->
            InputStreamReader(inputStream).use { reader ->
                gson.fromJson(reader, Array<HelpCategory>::class.java).toList()
            }
        }
    }
}
