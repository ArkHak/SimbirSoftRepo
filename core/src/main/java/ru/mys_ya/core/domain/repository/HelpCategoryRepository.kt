package ru.mys_ya.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mys_ya.core.domain.model.HelpCategory

interface HelpCategoryRepository {
    fun getHelpCategories(): Flow<List<HelpCategory>>
    fun setIdHelpCategoriesHideList(idHelpCategoryHideList: ArrayList<Int>)
    fun getIdHelpCategoriesHideList(): List<Int>
    suspend fun putDatabase(listCategories: List<HelpCategory>)
}
