package o.mysin.simbirsoftappjava.ui.news.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private var _newsList: List<News> = emptyList()

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(_newsList[position])
        holder.binding.itemNews.setOnClickListener {
            onItemClickListener?.onItemClick(_newsList[position].id)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun updateNewsList(newsList: List<News>) {
        val diffResult = DiffUtil.calculateDiff(NewsDiffUtilCallback(_newsList, newsList))
        _newsList = newsList
        diffResult.dispatchUpdatesTo(this)
    }
}

fun interface OnItemClickListener {
    fun onItemClick(idItem: Int)
}
