package ru.mys_ya.feature_news.ui.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.feature_help_api.model.HelpCategory
import ru.mys_ya.feature_news.databinding.ItemFilterBinding

class FilterAdapter(
    private val idHelpCategoryHideList: List<Int>,
    private val onItemClicked: (idItem: Int) -> Unit,
) :
    RecyclerView.Adapter<FilterViewHolder>() {

    private var _filterList: List<HelpCategory> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = ItemFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return FilterViewHolder(idHelpCategoryHideList,binding) {
            onItemClicked(_filterList[it].id)
        }
    }

    override fun getItemCount(): Int {
        return _filterList.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(_filterList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFilterList(filterList: List<HelpCategory>) {
        _filterList = filterList
        notifyDataSetChanged()
    }
}
