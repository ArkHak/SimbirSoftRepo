package o.mysin.simbirsoftappjava.data.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import o.mysin.simbirsoftappjava.data.network.ApiService
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.domain.utils.AssetManager

class HelpCategoryRepositoryImpl(
    private val assetManager: AssetManager,
    private val apiService: ApiService,
) : HelpCategoryRepository {

    private var filterHelpCategoryIdList = arrayListOf<Int>()

    override fun getHelpCategories(): Observable<List<HelpCategory>> {
        return getCategoryFromWeb()
            .onErrorReturnItem(getCategoryFromAssets())
    }

    override fun setFilterList(filterList: List<Int>) {
        filterHelpCategoryIdList.clear()
        filterHelpCategoryIdList.addAll(filterList)
    }

    override fun getFilterList(): List<Int> = filterHelpCategoryIdList

    fun setIdListFilter(idList: List<Int>) {
        filterHelpCategoryIdList.clear()
        filterHelpCategoryIdList.addAll(idList)
    }

    private fun getCategoryFromWeb(): Observable<List<HelpCategory>> {
        return apiService.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
