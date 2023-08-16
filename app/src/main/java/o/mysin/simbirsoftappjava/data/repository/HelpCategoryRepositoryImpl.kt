package o.mysin.simbirsoftappjava.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import o.mysin.simbirsoftappjava.data.db.dao.CategoryDao
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.domain.mapper.CategoryMapper
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class HelpCategoryRepositoryImpl(
    private val assetManager: AssetManager,
    private val apiService: ApiService,
    private val categoryDao: CategoryDao,
    private val mapper: CategoryMapper,
) : HelpCategoryRepository {

    private var idHelpCategoryHideList = arrayListOf<Int>()

    override fun getHelpCategories(): Flow<List<HelpCategory>> = flow {
        categoryDao.getAllCategories().map { category ->
            mapper.fromCategory(category)
        }.also { helpCategoriesFromBD ->
            if (helpCategoriesFromBD.isEmpty()) {
                apiService.getCategories().also {
                    putDatabase(it)
                    emit(it)
                }
            } else {
                emit(helpCategoriesFromBD)
            }
        }
    }.catch {
        getHelpCategoryFromAssets().also {
            putDatabase(it)
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

    override fun setIdHelpCategoriesHideList(idHelpCategoryHideList: ArrayList<Int>) {
        this.idHelpCategoryHideList = idHelpCategoryHideList
    }

    override fun getIdHelpCategoriesHideList(): List<Int> = idHelpCategoryHideList

    override suspend fun putDatabase(listCategories: List<HelpCategory>) {
        categoryDao.insertCategories(listCategories.map { helpCategory ->
            mapper.toCategory(helpCategory)
        })
    }

    private fun getHelpCategoryFromAssets(): List<HelpCategory> =
        assetManager.getHelpCategoryListFromAsset("categories.json")

}
