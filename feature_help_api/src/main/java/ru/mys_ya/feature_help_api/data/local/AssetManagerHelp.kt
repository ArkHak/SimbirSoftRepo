package ru.mys_ya.feature_help_api.data.local

import ru.mys_ya.feature_help_api.model.HelpCategory

fun interface AssetManagerHelp {
    fun getHelpCategoryListFromAsset(fileName: String): List<HelpCategory>
}
