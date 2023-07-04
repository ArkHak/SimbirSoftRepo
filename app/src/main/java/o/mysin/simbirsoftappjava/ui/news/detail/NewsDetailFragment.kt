package o.mysin.simbirsoftappjava.ui.news.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.databinding.FragmentNewsDetailBinding
import o.mysin.simbirsoftappjava.domain.model.News
import o.mysin.simbirsoftappjava.utils.correctDateTime
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val binding: FragmentNewsDetailBinding by viewBinding()
    private val newsDetailViewModel: NewsDetailViewModel by viewModel()
    private val args by navArgs<NewsDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsDetailViewModel.news.observe(viewLifecycleOwner) { renderData(it) }
        newsDetailViewModel.getNews(args.newsId)

        initActionButtons()
        binding.labelNewsDetail.text = args.newsId.toString()

    }

    private fun renderData(news: News) {
        with(binding) {
            labelNewsDetailToolbar.text = news.title
            labelNewsDetail.text = news.title
            timeNewsDetail.text = correctDateTime(news.startDateTime, news.endDateTime)
            titleOwnerNewsDetail.text = news.owner
            ownerAddress.text = news.ownerAddress
            ownerContacts.text = news.ownerContacts
            firstImageNews.setImageResource(news.picturesUrl[0])
            secondImageNews.setImageResource(news.picturesUrl[1])
            thirdImageNews.setImageResource(news.picturesUrl[2])
            newsDescription.text = news.fullDescription
        }
    }


    private fun initActionButtons() {
        binding.newsDetailToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
