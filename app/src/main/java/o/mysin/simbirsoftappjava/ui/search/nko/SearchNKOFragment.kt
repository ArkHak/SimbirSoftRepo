package o.mysin.simbirsoftappjava.ui.search.nko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.Event
import o.mysin.simbirsoftappjava.databinding.FragmentSearchByNkoBinding


class SearchNKOFragment : Fragment(R.layout.fragment_search_by_nko) {

    private val binding: FragmentSearchByNkoBinding by viewBinding()
    private val searchViewModel: SearchNKOViewModel by viewModels()
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
    }

    private fun renderData(nkoList: List<Event>) {
        adapter.updateEventList(nkoList)
    }

    private fun initRecycler() {
        binding.searchNkoItemRecyclerView.adapter = adapter

        val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)

        binding.searchNkoItemRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            ).apply { divider?.let { setDrawable(it) } }
        )

    }

}
