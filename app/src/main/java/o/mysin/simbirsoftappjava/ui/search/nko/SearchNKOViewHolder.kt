package o.mysin.simbirsoftappjava.ui.search.nko

import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.domain.model.SearchEvent
import o.mysin.simbirsoftappjava.databinding.ItemSearchResultBinding

class SearchNKOViewHolder(private val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(event: SearchEvent) {
        binding.itemTitle.text = event.title
    }
}
