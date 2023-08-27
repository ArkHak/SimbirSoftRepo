package ru.mys_ya.feature_news.ui.news.main

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.mys_ya.core.domain.model.News
import ru.mys_ya.core.utils.correctDateTime
import ru.mys_ya.feature_news.databinding.ItemNewsBinding

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
