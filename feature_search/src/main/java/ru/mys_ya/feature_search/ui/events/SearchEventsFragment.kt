package ru.mys_ya.feature_search.ui.events

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mys_ya.core.domain.model.SearchEvent
import ru.mys_ya.feature_search.R
import ru.mys_ya.feature_search.di.component.SearchEventComponentProvider
import ru.mys_ya.feature_search.databinding.FragmentSearchByEventsBinding
import ru.mys_ya.feature_search.ui.SearchFragmentsCommonViewModel
import javax.inject.Inject

class SearchEventsFragment : Fragment(R.layout.fragment_search_by_events) {

    private val binding: FragmentSearchByEventsBinding by viewBinding()
    private val commonViewModel: SearchFragmentsCommonViewModel by activityViewModels()

    @Inject
    lateinit var searchViewModel: SearchEventsViewModel
    private val adapter: SearchEventsAdapter by lazy { SearchEventsAdapter() }
    private var emptySearch = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as SearchEventComponentProvider)
            .getSearchEventFragment()
            .injectSearchEventFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.eventsList
            .observe(viewLifecycleOwner) { renderData(it) }

        commonViewModel.queryEvents
            .observe(viewLifecycleOwner) { renderView(it) }

        searchViewModel.errorMessage
            .observe(viewLifecycleOwner) { renderMessage(it.message) }

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

    private fun renderData(eventList: List<SearchEvent>) {
        if (!emptySearch) {
            if (eventList.isNotEmpty()) {
                dataScreenState()
            } else {
                dataEmptyScreenState()
            }
        }
        adapter.updateEventList(eventList)
    }

    private fun renderMessage(message: String) {
        if (message.isNotBlank()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecycler() {
        val itemDecorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val divider = ContextCompat.getDrawable(requireContext(),  ru.mys_ya.core.R.drawable.divider)
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
