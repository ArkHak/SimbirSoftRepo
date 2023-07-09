package o.mysin.simbirsoftappjava.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.domain.model.Friend
import o.mysin.simbirsoftappjava.databinding.ItemFriendsBinding

class ProfileAdapter :
    RecyclerView.Adapter<ProfileViewHolder>() {
    private var _friendList: List<Friend> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemFriendsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _friendList.size.coerceAtMost(3)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(_friendList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFriendsList(friendsList: List<Friend>) {
        _friendList = friendsList
        notifyDataSetChanged()
    }
}
