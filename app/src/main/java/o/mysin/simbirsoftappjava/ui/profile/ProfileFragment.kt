package o.mysin.simbirsoftappjava.ui.profile

import android.Manifest
import android.net.Uri
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
import o.mysin.simbirsoftappjava.data.entity.User
import o.mysin.simbirsoftappjava.databinding.FragmentProfileBinding
import o.mysin.simbirsoftappjava.utils.convertDateTimeProfile

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var adapter: ProfileAdapter

    private val permission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    cameraActivityLauncher.launch()
                }

                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    showWarningPictureToast()
                }

                else -> {
                    showWarningPictureToast()
                }
            }
        }


    private val cameraActivityLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            binding.profileAvatar.setImageBitmap(bitmap)
        }

    private val galleryActivityLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                binding.profileAvatar.setImageURI(uri)
            }
        }

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
            profileBirthday.text = convertDateTimeProfile(user.birthday)
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
            ProfileImageDialogFragment().show(childFragmentManager, IMAGE_SELECTOR)
        }

        childFragmentManager.setFragmentResultListener(
            REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            when (bundle.getInt(ITEM_KEY)) {
                ItemPhotoSelector.SELECT.ordinal -> takePhotoGallery()

                ItemPhotoSelector.CREATE.ordinal -> takePhotoCamera()

                ItemPhotoSelector.DELETE.ordinal -> profileViewModel.removeProfilePhoto()
            }
        }
    }

    private fun takePhotoGallery() {
        galleryActivityLauncher.launch("image/*")
    }

    private fun takePhotoCamera() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            showWarningPictureToast()
        } else {
            permission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun showWarningPictureToast() {
        Toast.makeText(
            requireContext(),
            resources.getText(R.string.warning_permission_create_photo),
            Toast.LENGTH_SHORT,
        ).show()
    }

    companion object {
        private const val IMAGE_SELECTOR = "IMAGE_SELECTOR"
        private const val REQUEST_KEY = "REQUEST_KEY"
        private const val ITEM_KEY = "ITEM_KEY"
    }
}
