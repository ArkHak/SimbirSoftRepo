package o.mysin.simbirsoftappjava.data

import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.db.HelpCategoryRepository
import o.mysin.simbirsoftappjava.data.entity.HelpCategory

class HelpCategoryRepositoryImpl : HelpCategoryRepository {
    private val _listHelpCategory = mutableListOf<HelpCategory>()

    init {
        _listHelpCategory.addAll(
            listOf(
                HelpCategory(
                    id = HelpCategoryFixList.CHILDREN.ordinal,
                    titleId = HelpCategoryFixList.getTitleIdByIndex(HelpCategoryFixList.CHILDREN.ordinal),
                    iconId = HelpCategoryFixList.getIconIdByIndex(HelpCategoryFixList.CHILDREN.ordinal)
                ),
                HelpCategory(
                    id = HelpCategoryFixList.ADULTS.ordinal,
                    titleId = HelpCategoryFixList.getTitleIdByIndex(HelpCategoryFixList.ADULTS.ordinal),
                    iconId = HelpCategoryFixList.getIconIdByIndex(HelpCategoryFixList.ADULTS.ordinal)
                ),
                HelpCategory(
                    id = HelpCategoryFixList.ELDERLY.ordinal,
                    titleId = HelpCategoryFixList.getTitleIdByIndex(HelpCategoryFixList.ELDERLY.ordinal),
                    iconId = HelpCategoryFixList.getIconIdByIndex(HelpCategoryFixList.ELDERLY.ordinal)
                ),
                HelpCategory(
                    id = HelpCategoryFixList.ANIMALS.ordinal,
                    titleId = HelpCategoryFixList.getTitleIdByIndex(HelpCategoryFixList.ANIMALS.ordinal),
                    iconId = HelpCategoryFixList.getIconIdByIndex(HelpCategoryFixList.ANIMALS.ordinal)
                ),
                HelpCategory(
                    id = HelpCategoryFixList.EVENTS.ordinal,
                    titleId = HelpCategoryFixList.getTitleIdByIndex(HelpCategoryFixList.EVENTS.ordinal),
                    iconId = HelpCategoryFixList.getIconIdByIndex(HelpCategoryFixList.EVENTS.ordinal)
                ),
            )
        )
    }

    override fun getHelpCategory(): List<HelpCategory> {
        return _listHelpCategory
    }

    override fun changeEnabledItemById(idItem: Int) {
        val index = _listHelpCategory.indexOfFirst { it.id == idItem }
        if (index != -1) {
            _listHelpCategory[index] =
                _listHelpCategory[index].copy(isEnabled = !_listHelpCategory[index].isEnabled)
        }
    }
}

enum class HelpCategoryFixList(val titleId: Int, val iconId: Int) {
    CHILDREN(titleId = R.string.title_help_children, iconId = R.mipmap.help_children),
    ADULTS(titleId = R.string.title_help_adults, iconId = R.mipmap.help_adults),
    ELDERLY(titleId = R.string.title_help_elderly, iconId = R.mipmap.help_elderly),
    ANIMALS(titleId = R.string.title_help_animals, iconId = R.mipmap.help_animals),
    EVENTS(titleId = R.string.title_help_events, iconId = R.mipmap.help_events);

    companion object {
        fun getTitleIdByIndex(index: Int): Int =
            HelpCategoryFixList.values().getOrNull(index)?.titleId ?: 0

        fun getIconIdByIndex(index: Int): Int =
            HelpCategoryFixList.values().getOrNull(index)?.iconId ?: 0
    }
}
