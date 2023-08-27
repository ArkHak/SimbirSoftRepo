package ru.mys_ya.feature_help.ui

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.mys_ya.core.domain.model.HelpCategory
import ru.mys_ya.feature_help.databinding.ItemHelpsBinding

class HelpViewHolder(private val binding: ItemHelpsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(help: HelpCategory) {
        binding.itemHelpTitle.text = help.title
        binding.itemHelpIcon.load(help.iconUrl) {
            crossfade(true)
            scale(Scale.FILL)
        }
    }
}
