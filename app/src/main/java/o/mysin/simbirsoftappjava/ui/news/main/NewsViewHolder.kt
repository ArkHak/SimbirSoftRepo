package o.mysin.simbirsoftappjava.ui.news.main

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import o.mysin.simbirsoftappjava.data.entity.News
import o.mysin.simbirsoftappjava.databinding.ItemNewsBinding
import o.mysin.simbirsoftappjava.utils.correctDateTime

class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News) {
        with(binding) {
            pictureNews.load(news.picturesUrl.first()) {
                crossfade(true)
                scale(Scale.FILL)
            }
            titleNews.text = news.title
            descriptionNews.text = news.description
            timeText.text = correctDateTime(news.startDateTime, news.endDateTime)
        }

    }
}
