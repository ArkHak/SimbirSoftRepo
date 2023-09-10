package ru.mys_ya.feature_help.data.local

import android.content.Context
import com.google.gson.Gson
import ru.mys_ya.feature_help_api.data.local.AssetManagerHelp
import ru.mys_ya.feature_help_api.model.HelpCategory
import java.io.InputStreamReader

class AssetManagerHelpImpl(
    private val gson: Gson,
    private val context: Context,
) : AssetManagerHelp{
    override fun getHelpCategoryListFromAsset(fileName: String): List<HelpCategory> {
        return context.assets.open(fileName).use { inputStream ->
            InputStreamReader(inputStream).use { reader ->
                gson.fromJson(reader, Array<HelpCategory>::class.java).toList()
            }
        }
    }
}
