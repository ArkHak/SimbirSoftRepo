package o.mysin.simbirsoftappjava.domain.utils

import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.model.News

interface AssetManager {
    fun getNewsListFromAsset(fileName: String): List<News>
    fun getHelpCategoryListFromAsset(fileName: String): List<HelpCategory>
}
