package ru.mys_ya.feature_news.ui.news.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import ru.mys_ya.core.domain.model.News
import ru.mys_ya.core.utils.correctDateTime
import ru.mys_ya.feature_news.R
import ru.mys_ya.feature_news.di.component.detail.NewsDetailComponentProvider
import ru.mys_ya.feature_news.databinding.FragmentNewsDetailBinding
import javax.inject.Inject

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val binding: FragmentNewsDetailBinding by viewBinding()

    @Inject
    lateinit var newsDetailViewModel: NewsDetailViewModel
    private val args by navArgs<NewsDetailFragmentArgs>()

     override fun onAttach(context: Context) {
            super.onAttach(context)
            (context.applicationContext as NewsDetailComponentProvider)
                .getNewsDetailComponent()
                .injectNewsDetailFragment(this)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsDetailViewModel.news.observe(viewLifecycleOwner) { renderData(it) }
        newsDetailViewModel.loadNews(args.newsId)

        initActionButtons()
        binding.labelNewsDetail.text = args.newsId.toString()

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
}
