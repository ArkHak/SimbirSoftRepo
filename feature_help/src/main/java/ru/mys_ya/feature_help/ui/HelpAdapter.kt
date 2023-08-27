package ru.mys_ya.feature_help.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mys_ya.core.domain.model.HelpCategory
import ru.mys_ya.feature_help.databinding.ItemHelpsBinding

class HelpAdapter :
    RecyclerView.Adapter<HelpViewHolder>() {
    private var _helpCategoryList: List<HelpCategory> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val binding = ItemHelpsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return HelpViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _helpCategoryList.size
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        holder.bind(_helpCategoryList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateHelpCategoryList(helpCategoryList: List<HelpCategory>) {
        _helpCategoryList = helpCategoryList
        notifyDataSetChanged()
    }
}
