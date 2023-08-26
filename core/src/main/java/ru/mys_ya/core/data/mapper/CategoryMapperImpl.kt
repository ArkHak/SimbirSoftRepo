package ru.mys_ya.core.data.mapper

import ru.mys_ya.core.data.db.entity.Category
import ru.mys_ya.core.domain.mapper.CategoryMapper
import ru.mys_ya.core.domain.model.HelpCategory

class CategoryMapperImpl : CategoryMapper {
    override fun toData(helpCategory: HelpCategory): Category =
        Category(
            id = helpCategory.id,
            title = helpCategory.title,
            iconUrl = helpCategory.iconUrl
        )

    override fun toDomain(category: Category): HelpCategory =
        HelpCategory(
            id = category.id,
            title = category.title,
            iconUrl = category.iconUrl
        )
}
