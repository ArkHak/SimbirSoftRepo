package ru.mys_ya.feature_search.ui.events

import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.feature_search.databinding.ItemSearchResultBinding
import ru.mys_ya.feature_search_api.model.SearchEvent

class SearchEventsViewHolder(private val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(event: SearchEvent) {
        binding.itemTitle.text = event.title
    }
}
