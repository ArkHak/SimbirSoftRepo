package ru.mys_ya.feature_help.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mys_ya.core.MainActivityViewModel
import ru.mys_ya.core.di.viewmodule.MultiViewModelFactory
import ru.mys_ya.core.domain.model.HelpCategory
import ru.mys_ya.core.utils.MarginDecoration
import ru.mys_ya.feature_help.R
import ru.mys_ya.feature_help.databinding.FragmentHelpBinding
import ru.mys_ya.feature_help.di.HelpComponentProvider
import javax.inject.Inject

class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding: FragmentHelpBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val helpViewModel by viewModels<HelpViewModel> {
        viewModelFactory
    }

    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val adapter: HelpAdapter by lazy { HelpAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as HelpComponentProvider)
            .getHelpComponent()
            .injectHelpFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.setHideBottomNavigation(false)

        helpViewModel.helpCategoryList
            .observe(viewLifecycleOwner) { renderData(it) }
        helpViewModel.errorMessage.observe(viewLifecycleOwner) { renderMessage(it.message) }

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

    private fun renderMessage(message: String) {
        if (message.isNotBlank()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
