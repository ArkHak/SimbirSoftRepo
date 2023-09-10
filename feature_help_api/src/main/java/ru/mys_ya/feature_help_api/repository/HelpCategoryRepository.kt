package ru.mys_ya.feature_help_api.repository

import kotlinx.coroutines.flow.Flow
import ru.mys_ya.feature_help_api.model.HelpCategory

interface HelpCategoryRepository {
    fun getHelpCategories(): Flow<List<HelpCategory>>
    fun setIdHelpCategoriesHideList(idHelpCategoryHideList: ArrayList<Int>)
    fun getIdHelpCategoriesHideList(): List<Int>
    suspend fun putDatabase(listCategories: List<HelpCategory>)
}
