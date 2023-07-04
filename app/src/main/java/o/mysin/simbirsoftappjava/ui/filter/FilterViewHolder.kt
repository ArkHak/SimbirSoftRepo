package o.mysin.simbirsoftappjava.ui.filter

import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.databinding.ItemFilterBinding

class FilterViewHolder(val binding: ItemFilterBinding, private val onItemClicked: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.itemFilter.setOnClickListener {
            onItemClicked(bindingAdapterPosition)
        }
    }

    fun bind(helpCategory: HelpCategory) {
        binding.itemFilter.text = helpCategory.title
        binding.itemFilter.isChecked = helpCategory.isEnabled
    }
}
