package o.mysin.simbirsoftappjava.ui.news

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import o.mysin.simbirsoftappjava.data.entity.News
import o.mysin.simbirsoftappjava.databinding.ItemNewsBinding

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News) {
        with(binding) {
            pictureNews.load(news.pictureUrl) {
                crossfade(true)
                scale(Scale.FILL)
            }
            titleNews.text = news.title
            descriptionNews.text = news.description
            timeText.text = news.dateTime.toString()
        }

    }
}
