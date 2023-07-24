package o.mysin.simbirsoftappjava.data.repository

import com.google.gson.Gson
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import java.io.InputStream
import java.io.InputStreamReader

class NewsRepositoryImpl(
    private val gson: Gson,
    private val inputStream: InputStream,
) : NewsRepository {

    private var _listNews = emptyList<News>()

    override fun getAllNews(): List<News> {
        return _listNews
    }

    override fun addListNews(listNews: List<News>) {
        _listNews = listNews
    }

    private fun getNewsFromAssets(): List<News> {
        val reader = InputStreamReader(inputStream)
        val listNews = gson.fromJson(reader, Array<News>::class.java).toList()
        reader.close()
        inputStream.close()
        return listNews
    }
}
