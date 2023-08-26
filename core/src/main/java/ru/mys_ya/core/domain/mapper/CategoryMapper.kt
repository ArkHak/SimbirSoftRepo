package ru.mys_ya.core.domain.mapper

import ru.mys_ya.core.data.db.entity.Category
import ru.mys_ya.core.domain.model.HelpCategory

interface CategoryMapper {
    fun toData(helpCategory: HelpCategory): Category
    fun toDomain(category: Category): HelpCategory
}
