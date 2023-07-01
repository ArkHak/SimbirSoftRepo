package o.mysin.simbirsoftappjava.ui.filter

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.entity.HelpCategory
import o.mysin.simbirsoftappjava.databinding.FragmentFilterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val binding: FragmentFilterBinding by viewBinding()
    private val filterViewModel: FilterViewModel by viewModel()
    private val adapter: FilterAdapter by lazy { FilterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterViewModel.filterList.observe(viewLifecycleOwner) { renderData(it) }

        initAdapter()
        initRecycler()
        initActionButtons()

    }

    private fun renderData(filterList: List<HelpCategory>) {
        adapter.updateFilterList(filterList)
    }

    private fun initAdapter() {
        adapter.setOnItemClickListener { idItem ->
            filterViewModel.changeValueItemFilter(idItem)
        }
    }

    private fun initRecycler() {
        binding.filterItemRecyclerView.adapter = adapter

        val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)

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
    }

}
