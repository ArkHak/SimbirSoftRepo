package ru.mys_ya.feature_help_api.mapper

import ru.mys_ya.core.data.db.entity.Category
import ru.mys_ya.feature_help_api.model.HelpCategory

interface CategoryMapper {
    fun toData(helpCategory: HelpCategory): Category
    fun toDomain(category: Category): HelpCategory
}
