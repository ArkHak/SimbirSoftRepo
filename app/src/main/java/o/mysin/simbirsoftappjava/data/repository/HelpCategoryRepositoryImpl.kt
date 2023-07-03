package o.mysin.simbirsoftappjava.data.repository

import com.google.gson.Gson
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import java.io.InputStream
import java.io.InputStreamReader

class HelpCategoryRepositoryImpl(
    private val gson: Gson,
    private val inputStream: InputStream,
) : HelpCategoryRepository {
    private val _listHelpCategory = mutableListOf<HelpCategory>()

    init {
        _listHelpCategory.addAll(getCategoryFromAssets())
    }

    override fun getHelpCategory(): List<HelpCategory> {
        return _listHelpCategory
    }

    override fun updateList(newListHelpCategory: List<HelpCategory>) {
        _listHelpCategory.zip(newListHelpCategory).forEachIndexed { index, (oldItem, newItem) ->
            if (oldItem.isEnabled != newItem.isEnabled) {
                _listHelpCategory[index] = newItem
            }
        }
    }

    private fun getCategoryFromAssets(): List<HelpCategory> {
        val reader = InputStreamReader(inputStream)
        val listCategory = gson.fromJson(reader, Array<HelpCategory>::class.java).toList()
        val correctList = listCategory.map {
            it.copy(
                iconUrl = HelpCategoryFixList.getIconIdByIndex(it.iconUrl),
                isEnabled = true
            )
        }
        reader.close()
        inputStream.close()
        return correctList
    }
}

enum class HelpCategoryFixList(val iconId: Int) {
    CHILDREN(iconId = R.mipmap.help_children),
    ADULTS(iconId = R.mipmap.help_adults),
    ELDERLY(iconId = R.mipmap.help_elderly),
    ANIMALS(iconId = R.mipmap.help_animals),
    EVENTS(iconId = R.mipmap.help_events);

    companion object {
        fun getIconIdByIndex(index: Int): Int =
            HelpCategoryFixList.values().getOrNull(index)?.iconId ?: R.mipmap.help_children
    }
}
