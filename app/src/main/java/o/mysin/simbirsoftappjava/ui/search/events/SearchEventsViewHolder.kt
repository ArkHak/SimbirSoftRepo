package o.mysin.simbirsoftappjava.ui.search.events

import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.databinding.ItemSearchResultBinding
import ru.mys_ya.core.domain.model.SearchEvent

class SearchEventsViewHolder(private val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(event: SearchEvent) {
        binding.itemTitle.text = event.title
    }
}
