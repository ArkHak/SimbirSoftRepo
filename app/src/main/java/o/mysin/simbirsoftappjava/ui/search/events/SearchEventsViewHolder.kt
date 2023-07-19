package o.mysin.simbirsoftappjava.ui.search.events

import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.databinding.ItemSearchResultBinding
import o.mysin.simbirsoftappjava.domain.model.Event

class SearchEventsViewHolder(private val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(event: Event) {
        binding.itemTitle.text = event.title
    }
}
