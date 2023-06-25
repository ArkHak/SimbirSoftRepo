package o.mysin.simbirsoftappjava.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentSearchByNkoBinding


class SearchByNKOFragment : Fragment(R.layout.fragment_search_by_nko) {

    private val binding: FragmentSearchByNkoBinding by viewBinding()
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter
    private var firstInit = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        repeatRandomLoadEvents()
    }

    private fun repeatRandomLoadEvents() {
        if (firstInit) {
            adapter.updateEventList(searchViewModel.getRandomList())
        }
    }

    private fun initAdapter() {
        firstInit = true
        adapter = SearchAdapter()
        adapter.updateEventList(searchViewModel.getRandomList())
        binding.searchNkoItemRecyclerView.adapter = adapter


        val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)

        binding.searchNkoItemRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
                .apply { divider?.let { setDrawable(it) } }
        )

    }


}
