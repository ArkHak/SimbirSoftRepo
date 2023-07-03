package o.mysin.simbirsoftappjava.domain.repository

import o.mysin.simbirsoftappjava.domain.model.HelpCategory

interface HelpCategoryRepository {
    fun getHelpCategory(): List<HelpCategory>
    fun updateList(newListHelpCategory: List<HelpCategory>)
}
