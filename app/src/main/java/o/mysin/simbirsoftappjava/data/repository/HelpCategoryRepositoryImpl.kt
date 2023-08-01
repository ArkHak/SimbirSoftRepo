package o.mysin.simbirsoftappjava.data.repository

import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class HelpCategoryRepositoryImpl(
    private val assetManager: AssetManager,
) : HelpCategoryRepository {
    private var _listHelpCategories: List<HelpCategory> = emptyList()

    override fun getHelpCategories(): List<HelpCategory> {
        if (_listHelpCategories.isEmpty()) {
            _listHelpCategories = getCategoryFromAssets()
        }
        return _listHelpCategories
    }

    override fun updateList(newListHelpCategory: List<HelpCategory>) {
        _listHelpCategories = newListHelpCategory
    }

    private fun getCategoryFromAssets(): List<HelpCategory> {
        val helpCategoryList = assetManager.getHelpCategoryListFromAsset("categories.json")
        val correctList = helpCategoryList.map {
            it.copy(
                isEnabled = true
            )
        }
        return correctList
    }
}
