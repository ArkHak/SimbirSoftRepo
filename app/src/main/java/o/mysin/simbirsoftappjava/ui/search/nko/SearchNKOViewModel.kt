package o.mysin.simbirsoftappjava.ui.search.nko

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import o.mysin.simbirsoftappjava.data.Event
import java.util.Random

class SearchNKOViewModel : ViewModel() {

    private val _nkoList: MutableLiveData<List<Event>> = MutableLiveData()
    val nkoList: LiveData<List<Event>>
        get() = _nkoList


    private val fooNKOEventsList = listOf(
        Event(title = "Благотворительный фонд Алины Кактотамовны"),
        Event(title = "\"Во имя жизни \""),
        Event(title = "Благотворительный фонд В. Потанина"),
        Event(title = "\"Детские домики \""),
        Event(title = "\"Мозайка счастья \""),
    )

    fun loadResultList() {
        val random = Random()
        val countEvents = random.nextInt(fooNKOEventsList.size)
        val numbersEvents = (fooNKOEventsList.indices).toList().shuffled(random).take(countEvents)
        val fooList = mutableListOf<Event>()
        numbersEvents.forEach {
            fooList.add(fooNKOEventsList[it])
        }

        viewModelScope.launch {
            _nkoList.value = fooList
        }
    }
}
