package o.mysin.simbirsoftappjava.ui.profile

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
        initAction()
    }

    private fun renderData(user: User) {
        with(binding) {
            profileName.text = user.name
            profileBirthday.text = user.birthday
            profileWorking.text = user.work
            user.avatarSrc?.let {
                profileAvatar.load(it) {
                    crossfade(true)
                }
            } ?: run {
                profileAvatar.load(R.drawable.no_photo)
            }
        }
        adapter.updateFriendsList(user.friendsList)
    }

    private fun initAdapter() {
        adapter = ProfileAdapter()
        binding.profileFriendRecyclerView.adapter = adapter
        binding.profileFriendRecyclerView.setHasFixedSize(true)
    }

    private fun initAction() {
        binding.profileAvatar.setOnClickListener {
            val imageDialogFragment = ProfileImageDialogFragment { item ->
                when (item) {
                    ItemPhotoSelector.SELECT -> {}

                    ItemPhotoSelector.CREATE -> takePhoto()

                    ItemPhotoSelector.DELETE -> profileViewModel.removeProfilePhoto()
                }
            }
            imageDialogFragment.show(childFragmentManager, IMAGE_SELECTOR)
        }
    }

    private fun takePhoto() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            warningPictureToast()
        } else {
            permission.launch(Manifest.permission.CAMERA)
        }
    }

    private val permission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    camera.launch()
                }

                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    warningPictureToast()
                }

                else -> {
                    warningPictureToast()
                }
            }
        }

    private val camera =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            binding.profileAvatar.setImageBitmap(bitmap)
        }

    private fun warningPictureToast() {
        Toast.makeText(
            binding.root.context,
            resources.getText(R.string.warning_permission_create_photo),
            Toast.LENGTH_SHORT,
        ).show()
    }

    companion object {
        private const val IMAGE_SELECTOR = "IMAGE_SELECTOR"
    }
}
