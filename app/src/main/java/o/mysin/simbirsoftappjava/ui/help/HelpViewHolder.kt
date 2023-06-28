package o.mysin.simbirsoftappjava.ui.help

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import o.mysin.simbirsoftappjava.data.entity.HelpCategory
import o.mysin.simbirsoftappjava.databinding.ItemHelpsBinding

class HelpViewHolder(private val binding: ItemHelpsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(help: HelpCategory) {
        binding.itemHelpTitle.text = binding.root.context.getString(help.titleId)
        binding.itemHelpIcon.load(help.iconId) {
            crossfade(true)
            scale(Scale.FILL)
        }
    }
}
