package ru.mys_ya.feature_help.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.mys_ya.core.database.dao.CategoryDao
import ru.mys_ya.feature_help_api.data.local.AssetManagerHelp
import ru.mys_ya.feature_help_api.mapper.CategoryMapper
import ru.mys_ya.feature_help_api.model.HelpCategory
import ru.mys_ya.feature_help_api.repository.HelpCategoryRepository
import ru.mys_ya.network.ApiService

class HelpCategoryRepositoryImpl(
    private val assetManager: AssetManagerHelp,
    private val apiService: ApiService,
    private val categoryDao: CategoryDao,
    private val mapper: CategoryMapper,
) : HelpCategoryRepository {

    private var idHelpCategoryHideList = arrayListOf<Int>()

    override fun getHelpCategories(): Flow<List<HelpCategory>> = flow {
        categoryDao.getAllCategories().map { category ->
            mapper.toDomain(category)
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
        categoryDao.insertCategories(
            listCategories.map { helpCategory ->
            mapper.toData(helpCategory)
        },
        )
    }

    private fun getHelpCategoryFromAssets(): List<HelpCategory> =
        assetManager.getHelpCategoryListFromAsset("categories.json")
}
