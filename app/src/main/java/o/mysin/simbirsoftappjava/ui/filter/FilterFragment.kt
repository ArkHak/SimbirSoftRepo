package o.mysin.simbirsoftappjava.ui.filter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.appComponent
import ru.mys_ya.core.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.databinding.FragmentFilterBinding
import javax.inject.Inject


class FilterFragment : Fragment(o.mysin.simbirsoftappjava.R.layout.fragment_filter) {

    private val binding: FragmentFilterBinding by viewBinding()

    @Inject
    lateinit var filterViewModel: FilterViewModel
    private lateinit var adapter: FilterAdapter

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
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

        val divider = ContextCompat.getDrawable(requireContext(),  ru.mys_ya.core.R.drawable.divider)

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
                requireContext().getText(o.mysin.simbirsoftappjava.R.string.filter_change_apply),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun renderData(filterList: List<HelpCategory>) {
        adapter.updateFilterList(filterList)
    }

}
