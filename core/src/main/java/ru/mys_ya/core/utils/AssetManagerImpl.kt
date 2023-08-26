package ru.mys_ya.core.utils

import android.content.Context
import com.google.gson.Gson
import ru.mys_ya.core.domain.model.HelpCategory
import ru.mys_ya.core.domain.model.News
import ru.mys_ya.core.domain.utils.AssetManager
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
