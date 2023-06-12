package o.mysin.simbirsoftappjava.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.Friend
import o.mysin.simbirsoftappjava.databinding.ItemFriendsBinding

class ProfileAdapter(private val friendList: List<Friend>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemFriendsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            binding.itemName.text = friend.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFriendsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friendList[position])
    }
}