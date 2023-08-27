package ru.mys_ya.feature_search.ui.nko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mys_ya.core.domain.model.SearchEvent
import ru.mys_ya.feature_search.R
import ru.mys_ya.feature_search.databinding.FragmentSearchByNkoBinding
import ru.mys_ya.feature_search.ui.SearchFragmentsCommonViewModel


class SearchNKOFragment : Fragment(R.layout.fragment_search_by_nko) {

    private val binding: FragmentSearchByNkoBinding by viewBinding()
    private val searchViewModel: SearchNKOViewModel by viewModels()
    private val commonViewModel: SearchFragmentsCommonViewModel by activityViewModels()
    private val adapter: SearchNKOAdapter by lazy { SearchNKOAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchViewModel.nkoList
            .observe(viewLifecycleOwner) { renderData(it) }

        initRecycler()
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.loadResultList()
        commonViewModel.correctTitleSearchView(R.string.hint_input_name_organization)
    }

    private fun renderData(nkoList: List<SearchEvent>) {
        adapter.updateEventList(nkoList)
    }

    private fun initRecycler() {
        val itemDecorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val divider = ContextCompat.getDrawable(requireContext(),  ru.mys_ya.core.R.drawable.divider)
        divider?.let { itemDecorator.setDrawable(it) }

        binding.searchNkoItemRecyclerView.adapter = adapter
        binding.searchNkoItemRecyclerView.addItemDecoration(itemDecorator)
    }
}
