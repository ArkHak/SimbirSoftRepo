package ru.mys_ya.feature_search.ui.nko

import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.core.domain.model.SearchEvent
import ru.mys_ya.feature_search.databinding.ItemSearchResultBinding

class SearchNKOViewHolder(private val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(event: SearchEvent) {
        binding.itemTitle.text = event.title
    }
}
