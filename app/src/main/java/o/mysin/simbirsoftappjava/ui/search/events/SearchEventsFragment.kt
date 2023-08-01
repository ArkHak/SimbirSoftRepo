package o.mysin.simbirsoftappjava.ui.search.events

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
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
    private var emptySearch = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.eventsList
            .observe(viewLifecycleOwner) { renderData(it) }

        commonViewModel.queryEvents
            .observe(viewLifecycleOwner) { renderView(it) }


        initRecycler()
        emptyScreenState()
    }

    override fun onResume() {
        super.onResume()
        commonViewModel.correctTitleSearchView(R.string.hint_input_title_event)
    }

    private fun renderView(query: String) {
        if (query.isEmpty()) {
            emptySearch = true
            emptyScreenState()
            adapter.cleanEventList()
            searchViewModel.cleanEventList()
        } else {
            emptySearch = false
            searchViewModel.searchEvents(query)
        }
    }

    private fun renderData(eventList: List<Event>) {
        if (!emptySearch) {
            if (eventList.isNotEmpty()) {
                dataScreenState()
            } else {
                dataEmptyScreenState()
            }
        }
        adapter.updateEventList(eventList)
    }

    private fun initRecycler() {
        val itemDecorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)
        divider?.let { itemDecorator.setDrawable(it) }

        binding.searchEventsItemRecyclerView.adapter = adapter
        binding.searchEventsItemRecyclerView.addItemDecoration(itemDecorator)
    }

    private fun emptyScreenState() {
        binding.initLayout.visibility = View.VISIBLE
        binding.recyclerViewLayout.visibility = View.GONE
        binding.eventsNotFoundTextView.visibility = View.GONE
    }

    private fun dataScreenState() {
        binding.initLayout.visibility = View.GONE
        binding.recyclerViewLayout.visibility = View.VISIBLE
        binding.eventsNotFoundTextView.visibility = View.GONE
    }

    private fun dataEmptyScreenState() {
        binding.initLayout.visibility = View.GONE
        binding.recyclerViewLayout.visibility = View.GONE
        binding.eventsNotFoundTextView.visibility = View.VISIBLE
    }

}
