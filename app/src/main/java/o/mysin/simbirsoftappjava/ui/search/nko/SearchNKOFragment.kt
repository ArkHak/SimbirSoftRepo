package o.mysin.simbirsoftappjava.ui.search.nko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.domain.model.SearchEvent
import o.mysin.simbirsoftappjava.databinding.FragmentSearchByNkoBinding
import o.mysin.simbirsoftappjava.ui.search.SearchFragmentsCommonViewModel


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
        val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)
        divider?.let { itemDecorator.setDrawable(it) }

        binding.searchNkoItemRecyclerView.adapter = adapter
        binding.searchNkoItemRecyclerView.addItemDecoration(itemDecorator)
    }
}
