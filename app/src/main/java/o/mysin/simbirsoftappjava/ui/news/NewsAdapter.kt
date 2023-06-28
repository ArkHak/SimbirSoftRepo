package o.mysin.simbirsoftappjava.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.entity.News
import o.mysin.simbirsoftappjava.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private var _newsList: List<News> = emptyList()

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
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNewsList(newsList: List<News>) {
        _newsList = newsList
        notifyDataSetChanged()
    }
}
