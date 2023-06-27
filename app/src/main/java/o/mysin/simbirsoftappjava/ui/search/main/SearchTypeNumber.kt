package o.mysin.simbirsoftappjava.ui.search.main

import o.mysin.simbirsoftappjava.R

enum class SearchTypeNumber(val titleId: Int) {
    EVENTS(R.string.tab_name_search_by_events),
    NKO(R.string.tab_name_search_by_nko);

    companion object {
        fun getTitleIdByIndex(index: Int): Int = values().getOrNull(index)?.titleId ?: 0
    }

}
