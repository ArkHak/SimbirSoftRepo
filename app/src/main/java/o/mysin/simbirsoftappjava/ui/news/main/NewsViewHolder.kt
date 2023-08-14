package o.mysin.simbirsoftappjava.ui.news.main

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.databinding.ItemNewsBinding
import o.mysin.simbirsoftappjava.utils.correctDateTime

class NewsViewHolder(val binding: ItemNewsBinding, private val onItemClicked: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onItemClicked(bindingAdapterPosition)
        }
    }

    fun bind(news: News) {
        with(binding) {
            pictureNews.load(news.photos.first()) {
                crossfade(true)
                scale(Scale.FILL)
            }
            titleNews.text = news.name
            descriptionNews.text = news.description
            timeText.text = correctDateTime(news.startDate, news.endDate)
        }
    }
}
