package ru.mys_ya.feature_news.ui.news.detail

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import ru.mys_ya.core.di.viewmodule.MultiViewModelFactory
import ru.mys_ya.feature_news_api.domain.News
import ru.mys_ya.core.utils.correctDateTime
import ru.mys_ya.feature_news.R
import ru.mys_ya.feature_news.databinding.FragmentNewsDetailBinding
import ru.mys_ya.feature_news.di.component.detail.NewsDetailComponentProvider
import ru.mys_ya.feature_news.ui.news.detail.dialog.HelpMoneyDialogFragment
import javax.inject.Inject

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val binding: FragmentNewsDetailBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val newsDetailViewModel by viewModels<NewsDetailViewModel> {
        viewModelFactory
    }

    private val args by navArgs<NewsDetailFragmentArgs>()

    private val permission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    newsDetailViewModel.sendHelpMoney()
                }

                !shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    showWarningPostNotificationToast()
                }

                else -> {
                    showWarningPostNotificationToast()
                }
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as NewsDetailComponentProvider)
            .getNewsDetailComponent()
            .injectNewsDetailFragment(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener(
            REQUEST_SEND_SUM_MONEY,
            this
        ) { _, bundle ->
            newsDetailViewModel.changeMoneyHelpEvent(
                eventId = newsDetailViewModel.news.value?.id ?: 0,
                eventName = newsDetailViewModel.news.value?.name ?: "",
                eventAmount = bundle.getInt(SEND_SUM_MONEY)
            )

            if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                showWarningPostNotificationToast()
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permission.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsDetailViewModel.news.observe(viewLifecycleOwner) { renderData(it) }
        newsDetailViewModel.loadNews(args.newsId)

        initActionButtons()
        binding.labelNewsDetail.text = args.newsId.toString()

        binding.btnHelpMoney.setOnClickListener {
            HelpMoneyDialogFragment().show(childFragmentManager, HelpMoneyDialogFragment.TAG)
        }
    }

    private fun renderData(news: News) {
        with(binding) {
            labelNewsDetailToolbar.text = news.name
            labelNewsDetail.text = news.name
            timeNewsDetail.text = correctDateTime(news.startDate, news.endDate)
            titleOwnerNewsDetail.text = news.organisation
            ownerAddress.text = news.address
            ownerContacts.text = news.phone
            firstImageNews.load(news.photos[0])
            secondImageNews.load(news.photos[1])
            thirdImageNews.load(news.photos[2])
            newsDescription.text = news.fullDescription
        }
    }

    private fun initActionButtons() {
        binding.newsDetailToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showWarningPostNotificationToast() {
        Toast.makeText(
            requireContext(),
            "Дайте разрешение",
            Toast.LENGTH_SHORT,
        ).show()
    }

    companion object {
        const val SEND_SUM_MONEY = "SEND_SUM_MONEY"
        const val REQUEST_SEND_SUM_MONEY = "REQUEST_SEND_SUM_MONEY"
    }
}
