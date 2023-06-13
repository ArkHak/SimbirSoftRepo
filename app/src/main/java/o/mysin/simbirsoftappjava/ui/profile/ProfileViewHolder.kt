package o.mysin.simbirsoftappjava.ui.profile

import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.Friend
import o.mysin.simbirsoftappjava.databinding.ItemFriendsBinding

class ProfileViewHolder(private val binding: ItemFriendsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Friend) {
        binding.itemName.text = friend.name
    }
}
