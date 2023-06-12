package o.mysin.simbirsoftappjava.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.Friend
import o.mysin.simbirsoftappjava.data.User
import o.mysin.simbirsoftappjava.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
            .observe(viewLifecycleOwner, Observer<User> { renderData(it) })
    }

    private fun renderData(user: User) {
        with(binding) {
            profileName.text = user.name
            profileBirthday.text = user.birthday
            profileWorking.text = user.work
            profileAvatar.load(R.drawable.temp_image_man) {
                crossfade(true)
            }
        }
        initAdapter(user.friendsList)
    }

    private fun initAdapter(friendsList: List<Friend>) {
        val adapter = ProfileAdapter(friendsList)
        binding.profileFriendRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.profileFriendRecyclerView.adapter = adapter
        binding.profileFriendRecyclerView.setHasFixedSize(true)

    }
}