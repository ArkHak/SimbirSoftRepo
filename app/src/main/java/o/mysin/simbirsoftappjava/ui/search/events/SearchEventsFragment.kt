package o.mysin.simbirsoftappjava.ui.search.events

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.domain.model.Event
import o.mysin.simbirsoftappjava.databinding.FragmentSearchByEventsBinding
import o.mysin.simbirsoftappjava.ui.search.SearchFragmentsCommonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchEventsFragment : Fragment(R.layout.fragment_search_by_events) {

    private val binding: FragmentSearchByEventsBinding by viewBinding()
    private val commonViewModel: SearchFragmentsCommonViewModel by activityViewModels()
    private val searchViewModel: SearchEventsViewModel by viewModel()
    private val adapter: SearchEventsAdapter by lazy { SearchEventsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commonViewModel.queryEvents
            .observe(viewLifecycleOwner) { searchViewModel.searchEvents(it) }

        searchViewModel.eventsList
            .observe(viewLifecycleOwner) { renderData(it) }

        initRecycler()
    }

    private fun renderData(eventList: List<Event>) {
        if (eventList.isNotEmpty()) {
            binding.initLayout.visibility = View.GONE
            binding.recyclerViewLayout.visibility = View.VISIBLE
        }
        adapter.updateEventList(eventList)
    }


    private fun initRecycler() {
        binding.searchEventsItemRecyclerView.adapter = adapter

        val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)

        binding.searchEventsItemRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            ).apply { divider?.let { setDrawable(it) } }
        )
    }

    override fun onResume() {
        super.onResume()
        commonViewModel.correctTitleSearchView(ID_TITLE)
    }

    companion object {
        private val ID_TITLE = R.string.hint_input_title_event
    }
}
