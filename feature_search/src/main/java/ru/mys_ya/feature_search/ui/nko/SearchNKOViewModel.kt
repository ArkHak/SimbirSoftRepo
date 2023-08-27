package ru.mys_ya.feature_search.ui.nko

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.core.domain.model.SearchEvent
import java.util.Random

class SearchNKOViewModel : ViewModel() {

    private val _nkoList: MutableLiveData<List<SearchEvent>> = MutableLiveData()
    val nkoList: LiveData<List<SearchEvent>>
        get() = _nkoList

    private val fooNKOEventsList = listOf(
        SearchEvent(title = "Благотворительный фонд Алины Кактотамовны"),
        SearchEvent(title = "\"Во имя жизни \""),
        SearchEvent(title = "Благотворительный фонд В. Потанина"),
        SearchEvent(title = "\"Детские домики \""),
        SearchEvent(title = "\"Мозайка счастья \""),
    )

    fun loadResultList() {
        val random = Random()
        val countEvents = random.nextInt(fooNKOEventsList.size)
        val numbersEvents = (fooNKOEventsList.indices).toList().shuffled(random).take(countEvents)
        val fooList = mutableListOf<SearchEvent>()
        numbersEvents.forEach {
            fooList.add(fooNKOEventsList[it])
        }

        viewModelScope.launch {
            _nkoList.value = fooList
        }
    }
}
