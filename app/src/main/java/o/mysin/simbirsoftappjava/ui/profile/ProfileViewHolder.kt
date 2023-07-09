package o.mysin.simbirsoftappjava.ui.profile

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import o.mysin.simbirsoftappjava.domain.model.Friend
import o.mysin.simbirsoftappjava.databinding.ItemFriendsBinding

class ProfileViewHolder(private val binding: ItemFriendsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Friend) {
        binding.itemName.text = friend.name
        binding.itemAvatar.load(friend.imageSrc) {
            crossfade(true)
            scale(Scale.FILL)
        }
    }
}
