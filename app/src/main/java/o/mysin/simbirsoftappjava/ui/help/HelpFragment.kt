package o.mysin.simbirsoftappjava.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.entity.HelpCategory
import o.mysin.simbirsoftappjava.databinding.FragmentHelpBinding
import o.mysin.simbirsoftappjava.utils.MarginDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel


class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding: FragmentHelpBinding by viewBinding()
    private val helpViewModel: HelpViewModel by viewModel()
    private val adapter: HelpAdapter by lazy { HelpAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        helpViewModel.helpCategoryList
            .observe(viewLifecycleOwner) { renderData(it) }

        initRecycler()
    }

    private fun renderData(helpCategoryList: List<HelpCategory>) {
        adapter.updateHelpCategoryList(helpCategoryList)
    }


    private fun initRecycler() {
        binding.helpItemRecyclerView.adapter = adapter

        binding.helpItemRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(MarginDecoration(binding.root.context))
        }
    }
}
