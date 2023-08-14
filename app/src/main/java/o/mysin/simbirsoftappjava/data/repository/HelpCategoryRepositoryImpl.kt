package o.mysin.simbirsoftappjava.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class HelpCategoryRepositoryImpl(
    private val assetManager: AssetManager,
    private val apiService: ApiService,
) : HelpCategoryRepository {

    private var idHelpCategoryHideList = arrayListOf<Int>()

    override fun getHelpCategories(): Flow<List<HelpCategory>> = flow {
        emit(apiService.getCategories())
    }.catch {
        assetManager.getHelpCategoryListFromAsset("categories.json")
    }.flowOn(Dispatchers.IO)

    override fun setIdHelpCategoriesHideList(idHelpCategoryHideList: ArrayList<Int>) {
        this.idHelpCategoryHideList = idHelpCategoryHideList
    }

    override fun getIdHelpCategoriesHideList(): List<Int> = idHelpCategoryHideList

}
