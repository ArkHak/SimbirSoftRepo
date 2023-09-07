package ru.mys_ya.feature_news.ui.news.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.feature_news_api.data.News
import ru.mys_ya.feature_news.databinding.ItemNewsBinding

class NewsAdapter(private val onItemClicked: (idItem: Int) -> Unit) :
    RecyclerView.Adapter<NewsViewHolder>() {

    private var _newsList: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding) {
            onItemClicked(_newsList[it].id)
        }
    }

    override fun getItemCount(): Int {
        return _newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(_newsList[position])
    }

    fun updateNewsList(newsList: List<News>) {
        val diffResult = DiffUtil.calculateDiff(NewsDiffUtilCallback(_newsList, newsList))
        _newsList = newsList
        diffResult.dispatchUpdatesTo(this)
    }
}
