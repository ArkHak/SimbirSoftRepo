package o.mysin.simbirsoftappjava.domain.repository

import io.reactivex.rxjava3.core.Observable
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

interface HelpCategoryRepository {
    fun getHelpCategories(): Observable<List<HelpCategory>>
    fun setIdHelpCategoriesHideList(idHelpCategoryHideList: ArrayList<Int>)
    fun getIdHelpCategoriesHideList(): List<Int>
}
