package o.mysin.simbirsoftappjava.ui.profile

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.domain.model.Friend
import o.mysin.simbirsoftappjava.databinding.ItemFriendsBinding

class ProfileViewHolder(private val binding: ItemFriendsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Friend) {
        binding.itemName.text = friend.name
        binding.itemAvatar.load(friend.imageSrc) {
            crossfade(true)
            scale(Scale.FILL)
            memoryCachePolicy(CachePolicy.DISABLED)
            placeholder(R.drawable.image_load)
            error(R.drawable.no_photo)
        }
    }
}
