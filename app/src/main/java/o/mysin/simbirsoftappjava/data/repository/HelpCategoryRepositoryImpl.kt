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

    private var idHelpCategoryHideList = arrayListOf<Int>()

    override fun getHelpCategories(): Observable<List<HelpCategory>> {
        return getCategoryFromWeb()
            .onErrorReturnItem(getCategoryFromAssets())
    }

    override fun setIdHelpCategoriesHideList(idHelpCategoryHideList: ArrayList<Int>) {
        this.idHelpCategoryHideList = idHelpCategoryHideList
    }

    override fun getIdHelpCategoriesHideList(): List<Int> = idHelpCategoryHideList

    private fun getCategoryFromWeb(): Observable<List<HelpCategory>> {
        return apiService.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getCategoryFromAssets(): List<HelpCategory> {
        return assetManager.getHelpCategoryListFromAsset("categories.json")
    }
}
