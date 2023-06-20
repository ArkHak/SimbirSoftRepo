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
    private lateinit var adapter: HelpAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        adapter = HelpAdapter()
        adapter.updateHelpCategoryList(helpViewModel.loadHelpCategory())
        binding.helpItemRecyclerView.adapter = adapter

        binding.helpItemRecyclerView.setHasFixedSize(true)
        binding.helpItemRecyclerView.addItemDecoration(MarginDecoration(binding.root.context))
    }


}
