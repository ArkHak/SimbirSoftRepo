package o.mysin.simbirsoftappjava.data.repository

import com.google.gson.Gson
import o.mysin.simbirsoftappjava.domain.repository.HelpCategoryRepository
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import java.io.InputStream
import java.io.InputStreamReader

class HelpCategoryRepositoryImpl(
    private val gson: Gson,
    private val inputStream: InputStream,
) : HelpCategoryRepository {
    private var _listHelpCategories: List<HelpCategory> = emptyList()

    override fun getHelpCategories(): List<HelpCategory> {
        if (_listHelpCategories.isEmpty()) {
            _listHelpCategories = getCategoryFromAssets()
        }
        return _listHelpCategories
    }

    override fun updateList(newListHelpCategory: List<HelpCategory>) {
        _listHelpCategories = newListHelpCategory
    }

    private fun getCategoryFromAssets(): List<HelpCategory> {
        val reader = InputStreamReader(inputStream)
        val listCategory = gson.fromJson(reader, Array<HelpCategory>::class.java).toList()
        val correctList = listCategory.map {
            it.copy(
                isEnabled = true
            )
        }
        reader.close()
        inputStream.close()
        return correctList
    }
}
