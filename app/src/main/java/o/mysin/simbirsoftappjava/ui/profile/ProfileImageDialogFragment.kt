package o.mysin.simbirsoftappjava.ui.profile

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import o.mysin.simbirsoftappjava.databinding.DialogFragmentImageSelectorBinding

class ProfileImageDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentImageSelectorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogFragmentImageSelectorBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)
        initItemsClickListener()
        return builder.create()
    }

    private fun initItemsClickListener() {
        binding.itemSelectPhoto.setOnClickListener {
            val resultData = bundleOf(ITEM_KEY to ItemPhotoSelector.SELECT.ordinal)
            parentFragmentManager.setFragmentResult(REQUEST_KEY, resultData)
            dismiss()
        }

        binding.itemCreatePhoto.setOnClickListener {
            val resultData = bundleOf(ITEM_KEY to ItemPhotoSelector.CREATE.ordinal)
            parentFragmentManager.setFragmentResult(REQUEST_KEY, resultData)
            dismiss()
        }

        binding.itemDeletePhoto.setOnClickListener {
            val resultData = bundleOf(ITEM_KEY to ItemPhotoSelector.DELETE.ordinal)
            parentFragmentManager.setFragmentResult(REQUEST_KEY, resultData)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val REQUEST_KEY = "REQUEST_KEY"
        private const val ITEM_KEY = "ITEM_KEY"
    }
}

enum class ItemPhotoSelector {
    SELECT,
    CREATE,
    DELETE,
}
