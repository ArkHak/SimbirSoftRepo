package ru.mys_ya.feature_help.mapper

import ru.mys_ya.core.data.db.entity.Category
import ru.mys_ya.feature_help_api.mapper.CategoryMapper
import ru.mys_ya.feature_help_api.model.HelpCategory

class CategoryMapperImpl : CategoryMapper {
    override fun toData(helpCategory: HelpCategory): Category =
        Category(
            id = helpCategory.id,
            title = helpCategory.title,
            iconUrl = helpCategory.iconUrl,
        )

    override fun toDomain(category: Category): HelpCategory =
        HelpCategory(
            id = category.id,
            title = category.title,
            iconUrl = category.iconUrl,
        )
}
