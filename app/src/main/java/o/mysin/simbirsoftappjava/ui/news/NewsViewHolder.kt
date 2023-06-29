package o.mysin.simbirsoftappjava.ui.news

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import o.mysin.simbirsoftappjava.data.entity.News
import o.mysin.simbirsoftappjava.databinding.ItemNewsBinding
import o.mysin.simbirsoftappjava.utils.getDaysStartEndDate
import o.mysin.simbirsoftappjava.utils.convertDateNews
import o.mysin.simbirsoftappjava.utils.getPeriodStartEndDate

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News) {
        with(binding) {
            pictureNews.load(news.pictureUrl) {
                crossfade(true)
                scale(Scale.FILL)
            }
            titleNews.text = news.title
            descriptionNews.text = news.description
            timeText.text = correctDateTime(news.startDateTime, news.endDateTime)
        }

    }

    private fun correctDateTime(startDateTime: Long, endDateTime: Long): String {
        val countDays = getDaysStartEndDate(startDateTime, endDateTime)
        return if (countDays > 0) {
            "Осталось $countDays дней ${getPeriodStartEndDate(startDateTime, endDateTime)}"
        } else {
            convertDateNews(startDateTime)
        }
    }
}
