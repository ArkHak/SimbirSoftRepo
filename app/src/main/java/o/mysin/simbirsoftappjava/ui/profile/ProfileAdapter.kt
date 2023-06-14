package o.mysin.simbirsoftappjava.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.Friend
import o.mysin.simbirsoftappjava.databinding.ItemFriendsBinding

class ProfileAdapter(private val friendList: List<Friend>) :
    RecyclerView.Adapter<ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemFriendsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        val height = parent.measuredHeight / 3
        binding.root.minimumHeight = height
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return friendList.size.coerceAtMost(3)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(friendList[position])
    }
}
