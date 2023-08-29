package ru.mys_ya.feature_news.ui.filter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mys_ya.core.domain.model.HelpCategory
import ru.mys_ya.feature_news.R
import ru.mys_ya.feature_news.di.component.filter.FilterComponentProvider
import ru.mys_ya.feature_news.databinding.FragmentFilterBinding
import javax.inject.Inject


class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val binding: FragmentFilterBinding by viewBinding()

    @Inject
    lateinit var filterViewModel: FilterViewModel
    private lateinit var adapter: FilterAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as FilterComponentProvider)
            .getFilterComponent()
            .injectFilterFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initRecycler()
        initActionButtons()

        filterViewModel.filterList.observe(viewLifecycleOwner) { renderData(it) }
    }

    private fun initAdapter() {
        adapter = FilterAdapter(filterViewModel.tmpIdHelpCategoryHideList) { idItem ->
            filterViewModel.changeIdHelpCategoryHideList(idItem)
        }
    }

    private fun initRecycler() {
        binding.filterItemRecyclerView.adapter = adapter

        val divider = ContextCompat.getDrawable(requireContext(), ru.mys_ya.core.R.drawable.divider)

        binding.filterItemRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            ).apply { divider?.let { setDrawable(it) } }
        )
    }

    private fun initActionButtons() {
        binding.filterToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.applyFilterSettings.setOnClickListener {
            filterViewModel.saveFilterList()
            Toast.makeText(
                context,
                requireContext().getText(R.string.filter_change_apply),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun renderData(filterList: List<HelpCategory>) {
        adapter.updateFilterList(filterList)
    }

}
