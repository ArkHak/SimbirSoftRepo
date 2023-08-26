package o.mysin.simbirsoftappjava.ui.help

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.appComponent
import ru.mys_ya.core.domain.model.HelpCategory
import o.mysin.simbirsoftappjava.databinding.FragmentHelpBinding
import ru.mys_ya.core.utils.MarginDecoration
import ru.mys_ya.core.MainActivityViewModel
import javax.inject.Inject

class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding: FragmentHelpBinding by viewBinding()

    @Inject
    lateinit var helpViewModel: HelpViewModel
    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val adapter: HelpAdapter by lazy { HelpAdapter() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.setHideBottomNavigation(false)

        helpViewModel.helpCategoryList
            .observe(viewLifecycleOwner) { renderData(it) }
        helpViewModel.errorMessage.observe(viewLifecycleOwner) { renderMessage(it.message) }


        initRecycler()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        helpViewModel.updateData()
        showLoadingData()
    }

    private fun renderData(helpCategoryList: List<HelpCategory>) {
        if (helpCategoryList.isNotEmpty()) {
            showData()
            adapter.updateHelpCategoryList(helpCategoryList)
        }
    }

    private fun renderMessage(message: String) {
        if (message.isNotBlank()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecycler() {
        binding.helpItemRecyclerView.adapter = adapter

        binding.helpItemRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(MarginDecoration(binding.root.context))
        }
    }

    private fun showLoadingData() {
        binding.loadingProgressBar.visibility = View.VISIBLE
        binding.helpItemRecyclerView.visibility = View.GONE
    }

    private fun showData() {
        binding.loadingProgressBar.visibility = View.GONE
        binding.helpItemRecyclerView.visibility = View.VISIBLE
    }
}
