package ru.mys_ya.core.domain.utils

import ru.mys_ya.core.domain.model.HelpCategory
import ru.mys_ya.core.domain.model.News

interface AssetManager {
    fun getNewsListFromAsset(fileName: String): List<News>
    fun getHelpCategoryListFromAsset(fileName: String): List<HelpCategory>
}
