package o.mysin.simbirsoftappjava.domain.repository

import kotlinx.coroutines.flow.Flow
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

interface HelpCategoryRepository {
    fun getHelpCategories(): Flow<List<HelpCategory>>
    fun setIdHelpCategoriesHideList(idHelpCategoryHideList: ArrayList<Int>)
    fun getIdHelpCategoriesHideList(): List<Int>
    suspend fun putDatabase(listCategories: List<HelpCategory>)
}
