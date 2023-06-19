package o.mysin.simbirsoftappjava.ui.profile

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import o.mysin.simbirsoftappjava.R

class ProfileImageDialogFragment(
    val clickListenerCreatePhoto: (ItemPhotoSelector) -> Unit,
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment_image_selector, null)
        builder.setView(view)

        initItemClickListener(view)

        return builder.create()
    }

    private fun initItemClickListener(view: View) {
        view.findViewById<View>(R.id.item_select_photo).apply {
            setOnClickListener {
                dismiss()
                clickListenerCreatePhoto(ItemPhotoSelector.SELECT)
            }
        }

        view.findViewById<View>(R.id.item_create_photo).apply {
            setOnClickListener {
                dismiss()
                clickListenerCreatePhoto(ItemPhotoSelector.CREATE)
            }
        }

        view.findViewById<View>(R.id.item_delete_photo).apply {
            setOnClickListener {
                dismiss()
                clickListenerCreatePhoto(ItemPhotoSelector.DELETE)
            }
        }
    }
}

enum class ItemPhotoSelector {
    SELECT,
    CREATE,
    DELETE,
}
