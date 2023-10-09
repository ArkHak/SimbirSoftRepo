package ru.mys_ya.feature_news.ui.news.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.coroutines.launch
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_AMOUNT
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_ID
import ru.mys_ya.feature_news.ui.news.detail.worker.SendWorker.Companion.EVENT_NAME
import ru.mys_ya.feature_news_api.domain.MoneyHelpEvent
import ru.mys_ya.feature_news_api.domain.News
import ru.mys_ya.feature_news_api.usecase.GetNewsDetailUseCase
import javax.inject.Inject


class NewsDetailViewModel @Inject constructor(
    private val newsDetailUseCase: GetNewsDetailUseCase,
    private val workManager: WorkManager,
) : ViewModel() {

    private val _news: MutableLiveData<News> = MutableLiveData()
    val news: LiveData<News>
        get() = _news

    private lateinit var moneyHelpEvent: MoneyHelpEvent

    fun loadNews(newsId: Int) {
        viewModelScope.launch {
            try {
                _news.value = newsDetailUseCase.invoke(newsId)
            } catch (error: Exception) {
                Log.e("MOD_TAG", "loadNews: $error")
            }
        }
    }

    fun changeMoneyHelpEvent(eventId: Int, eventName: String, eventAmount: Int) {
        moneyHelpEvent = MoneyHelpEvent(eventId, eventName, eventAmount)
    }

    fun sendHelpMoney() {
        val inputData = workDataOf(
            EVENT_ID to moneyHelpEvent.eventId,
            EVENT_NAME to moneyHelpEvent.eventName,
            EVENT_AMOUNT to moneyHelpEvent.eventAmount
        )

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val send = OneTimeWorkRequestBuilder<SendWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        workManager.enqueue(send)
    }
}
