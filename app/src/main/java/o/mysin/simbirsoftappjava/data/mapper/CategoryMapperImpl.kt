package o.mysin.simbirsoftappjava.data.mapper

import o.mysin.simbirsoftappjava.data.db.entity.Category
import o.mysin.simbirsoftappjava.domain.mapper.CategoryMapper
import o.mysin.simbirsoftappjava.domain.model.HelpCategory

class CategoryMapperImpl : CategoryMapper {
    override fun toCategory(helpCategory: HelpCategory): Category =
        Category(
            id = helpCategory.id,
            title = helpCategory.title,
            iconUrl = helpCategory.iconUrl
        )

    override fun fromCategory(category: Category): HelpCategory =
        HelpCategory(
            id = category.id,
            title = category.title,
            iconUrl = category.iconUrl
        )
}
