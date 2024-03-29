package ru.mys_ya.feature_news.ui.news.main

import androidx.recyclerview.widget.DiffUtil
import ru.mys_ya.feature_news_api.domain.News

class NewsDiffUtilCallback(private val oldList: List<News>, private val newList: List<News>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}


