package ru.mys_ya.feature_profile.ui

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import ru.mys_ya.core.domain.model.Friend
import ru.mys_ya.core.R.*
import ru.mys_ya.feature_profile.databinding.ItemFriendsBinding

class ProfileViewHolder(private val binding: ItemFriendsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Friend) {
        binding.itemName.text = friend.name
        binding.itemAvatar.load(friend.imageSrc) {
            crossfade(true)
            scale(Scale.FILL)
            memoryCachePolicy(CachePolicy.DISABLED)
            placeholder( drawable.image_load)
            error( drawable.no_photo)
        }
    }
}
