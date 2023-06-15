package o.mysin.simbirsoftappjava.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.Friend
import o.mysin.simbirsoftappjava.databinding.ItemFriendsBinding

class ProfileAdapter :
    RecyclerView.Adapter<ProfileViewHolder>() {
    private var friendList: List<Friend> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemFriendsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return friendList.size.coerceAtMost(3)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(friendList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFriendsList(newFriendsList: List<Friend>) {
        friendList = newFriendsList
        notifyDataSetChanged()
    }
}
