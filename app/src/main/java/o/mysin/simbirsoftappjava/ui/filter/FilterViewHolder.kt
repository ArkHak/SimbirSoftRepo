package o.mysin.simbirsoftappjava.ui.filter

import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.core.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.databinding.ItemFilterBinding

class FilterViewHolder(
    private val idHelpCategoryHideList: List<Int>,
    val binding: ItemFilterBinding,
    private val onItemClicked: (Int) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.itemFilter.setOnClickListener {
            onItemClicked(bindingAdapterPosition)
        }
    }

    fun bind(helpCategory: HelpCategory) {
        binding.itemFilter.text = helpCategory.title
        binding.itemFilter.isChecked = !idHelpCategoryHideList.contains(helpCategory.id)
    }
}
