package o.mysin.simbirsoftappjava.domain.mapper

import o.mysin.simbirsoftappjava.data.db.entity.Category
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

interface CategoryMapper {
    fun toCategory(helpCategory: HelpCategory): Category
    fun fromCategory(category: Category): HelpCategory
}
