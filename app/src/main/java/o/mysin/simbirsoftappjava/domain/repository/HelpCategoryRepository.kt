package o.mysin.simbirsoftappjava.domain.repository

import io.reactivex.rxjava3.core.Observable
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

interface HelpCategoryRepository {
    fun getHelpCategories(): Observable<List<HelpCategory>>

    fun setFilterList(filterList: List<Int>)
    fun getFilterList(): List<Int>
}
