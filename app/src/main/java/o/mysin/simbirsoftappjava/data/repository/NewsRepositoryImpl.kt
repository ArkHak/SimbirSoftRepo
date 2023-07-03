package o.mysin.simbirsoftappjava.data.repository

import com.google.gson.Gson
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.repository.NewsPictureList.Companion.getNewsPictureById
import o.mysin.simbirsoftappjava.domain.repository.NewsRepository
import o.mysin.simbirsoftappjava.domain.model.News
import java.io.InputStream
import java.io.InputStreamReader

class NewsRepositoryImpl(
    private val gson: Gson,
    private val inputStream: InputStream,
) : NewsRepository {

    private val _listNews = mutableListOf<News>()

    init {
        _listNews.addAll(getNewsFromAssets())
    }

    override fun getAllNews(): List<News> {
        return _listNews
    }

    override fun getNewsByFilter(filter: List<Int>): List<News> {
        return _listNews
            .filter { it.listHelpCategoryId.intersect(filter.toSet()).isNotEmpty() }
    }

    override fun addNews(news: News) {
        _listNews.add(news)
    }

    override fun getNewsById(newsId: Int): News? {
        return _listNews.find { it.id == newsId }
    }

    private fun getNewsFromAssets(): List<News> {
        val reader = InputStreamReader(inputStream)
        val listNews = gson.fromJson(reader, Array<News>::class.java).toList()
        val correctList = listNews.map {
            it.copy(picturesUrl = it.picturesUrl.map { url ->
                getNewsPictureById(url)
            })
        }
        reader.close()
        inputStream.close()
        return correctList
    }
}

enum class NewsPictureList(val pictureUrl: Int) {
    CHILDREN(pictureUrl = R.drawable.tmp_news_picture),
    EVENTS(pictureUrl = R.drawable.tmp_two_news_picture),
    SECOND(pictureUrl = R.drawable.tmp_news_picture_2),
    THREE(pictureUrl = R.drawable.tmp_news_picture_3);

    companion object {
        fun getNewsPictureById(index: Int): Int =
            NewsPictureList.values().getOrNull(index)?.pictureUrl ?: R.drawable.tmp_news_picture
    }
}
