package o.mysin.simbirsoftappjava.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.User
import o.mysin.simbirsoftappjava.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var adapter: ProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.userProfile
            .observe(viewLifecycleOwner) { renderData(it) }

        initAdapter()
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
        adapter.updateFriendsList(user.friendsList)
    }

    private fun initAdapter() {
        adapter = ProfileAdapter()
        binding.profileFriendRecyclerView.adapter = adapter
        binding.profileFriendRecyclerView.setHasFixedSize(true)
    }
}
