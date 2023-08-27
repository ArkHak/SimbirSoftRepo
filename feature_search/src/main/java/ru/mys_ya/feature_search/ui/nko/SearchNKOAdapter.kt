package ru.mys_ya.feature_search.ui.nko

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.core.domain.model.SearchEvent
import ru.mys_ya.feature_search.databinding.ItemSearchResultBinding

class SearchNKOAdapter : RecyclerView.Adapter<SearchNKOViewHolder>() {

    private var _eventList: List<SearchEvent> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNKOViewHolder {
        val binding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return SearchNKOViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _eventList.size
    }

    override fun onBindViewHolder(holder: SearchNKOViewHolder, position: Int) {
        holder.bind(_eventList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateEventList(eventCategoryList: List<SearchEvent>) {
        _eventList = eventCategoryList
        notifyDataSetChanged()
    }
}
