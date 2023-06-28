package o.mysin.simbirsoftappjava.ui.filter

import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.entity.HelpCategory
import o.mysin.simbirsoftappjava.databinding.ItemFilterBinding


class FilterViewHolder(val binding: ItemFilterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(helpCategory: HelpCategory) {
        binding.itemFilter.text = binding.root.context.getString(helpCategory.titleId)
        binding.itemFilter.isChecked = helpCategory.isEnabled
    }
}
