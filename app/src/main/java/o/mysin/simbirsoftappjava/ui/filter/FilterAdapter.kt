package o.mysin.simbirsoftappjava.ui.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.databinding.ItemFilterBinding

class FilterAdapter : RecyclerView.Adapter<FilterViewHolder>() {

    private var _filterList: List<HelpCategory> = emptyList()

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = ItemFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return FilterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _filterList.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(_filterList[position])
        holder.binding.itemFilter.setOnClickListener {
            onItemClickListener?.onItemClick(_filterList[position].id)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFilterList(filterList: List<HelpCategory>) {
        _filterList = filterList
        notifyDataSetChanged()
    }
}

fun interface OnItemClickListener {
    fun onItemClick(idItem: Int)
}
