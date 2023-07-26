package o.mysin.simbirsoftappjava.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.databinding.FragmentHelpBinding
import o.mysin.simbirsoftappjava.ui.MainActivityViewModel
import o.mysin.simbirsoftappjava.utils.MarginDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding: FragmentHelpBinding by viewBinding()
    private val helpViewModel: HelpViewModel by viewModel()
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val adapter: HelpAdapter by lazy { HelpAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.setHideBottomNavigation(false)

        helpViewModel.helpCategoryList
            .observe(viewLifecycleOwner) { renderData(it) }

        initRecycler()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        helpViewModel.updateData()
        showLoadingData()
    }

    private fun renderData(helpCategoryList: List<HelpCategory>) {
        if (helpCategoryList.isNotEmpty()) {
            showData()
            adapter.updateHelpCategoryList(helpCategoryList)
        }
    }


    private fun initRecycler() {
        binding.helpItemRecyclerView.adapter = adapter

        binding.helpItemRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(MarginDecoration(binding.root.context))
        }
    }

    private fun showLoadingData() {
        binding.loadingProgressBar.visibility = View.VISIBLE
        binding.helpItemRecyclerView.visibility = View.GONE
    }

    private fun showData() {
        binding.loadingProgressBar.visibility = View.GONE
        binding.helpItemRecyclerView.visibility = View.VISIBLE
    }
}
