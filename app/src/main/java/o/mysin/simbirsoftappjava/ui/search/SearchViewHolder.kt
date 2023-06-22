package o.mysin.simbirsoftappjava.ui.search

import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.Event
import o.mysin.simbirsoftappjava.databinding.ItemSearchResultBinding

class SearchViewHolder(private val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(event: Event) {
        binding.itemTitle.text = event.title
    }
}
