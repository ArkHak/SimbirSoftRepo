package o.mysin.simbirsoftappjava.domain.mapper

import o.mysin.simbirsoftappjava.data.db.entity.Category
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

interface CategoryMapper {
    fun toData(helpCategory: HelpCategory): Category
    fun toDomain(category: Category): HelpCategory
}
