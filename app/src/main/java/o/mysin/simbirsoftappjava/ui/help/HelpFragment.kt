package o.mysin.simbirsoftappjava.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentHelpBinding
import o.mysin.simbirsoftappjava.utils.MarginDecoration


class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding: FragmentHelpBinding by viewBinding()
    private val helpViewModel: HelpViewModel by viewModels()
    private val adapter: HelpAdapter by lazy { HelpAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        updateData()
    }


    private fun initRecycler() {
        binding.helpItemRecyclerView.adapter = adapter

        binding.helpItemRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(MarginDecoration(binding.root.context))
        }
    }

    private fun updateData() {
        adapter.updateHelpCategoryList(helpViewModel.loadHelpCategory())
    }
}
