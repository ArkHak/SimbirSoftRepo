package ru.mys_ya.feature_search.ui.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.core.domain.model.SearchEvent
import ru.mys_ya.feature_search.databinding.ItemSearchResultBinding

class SearchEventsAdapter : RecyclerView.Adapter<SearchEventsViewHolder>() {

    private var _eventList: List<SearchEvent> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchEventsViewHolder {
        val binding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return SearchEventsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _eventList.size
    }

    override fun onBindViewHolder(holder: SearchEventsViewHolder, position: Int) {
        holder.bind(_eventList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateEventList(eventList: List<SearchEvent>) {
        _eventList = eventList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun cleanEventList() {
        _eventList = emptyList()
        notifyDataSetChanged()
    }
}
