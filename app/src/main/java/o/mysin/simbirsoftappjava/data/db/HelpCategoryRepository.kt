package o.mysin.simbirsoftappjava.data.db

import o.mysin.simbirsoftappjava.data.entity.HelpCategory

interface HelpCategoryRepository {
    fun getHelpCategory(): List<HelpCategory>
    fun changeEnabledItemById(idItem: Int)
}
