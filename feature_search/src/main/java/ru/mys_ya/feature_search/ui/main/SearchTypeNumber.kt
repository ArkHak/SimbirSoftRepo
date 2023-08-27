package ru.mys_ya.feature_search.ui.main

import ru.mys_ya.feature_search.R

enum class SearchTypeNumber(val titleId: Int) {
    EVENTS(R.string.tab_name_search_by_events),
    NKO(R.string.tab_name_search_by_nko);

    companion object {
        fun getTitleIdByIndex(index: Int): Int = values().getOrNull(index)?.titleId ?: 0
    }

}
