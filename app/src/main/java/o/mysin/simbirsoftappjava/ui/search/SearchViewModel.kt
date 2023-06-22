package o.mysin.simbirsoftappjava.ui.search

import androidx.lifecycle.ViewModel
import o.mysin.simbirsoftappjava.data.Event
import java.util.Random

class SearchViewModel : ViewModel() {


    fun getRandomList(): List<Event> {
        val random = Random()
        val countEvents = random.nextInt(fooEventsList.size)
        val numbersEvents = (fooEventsList.indices).toList().shuffled(random).take(countEvents)
        val fooList = mutableListOf<Event>()
        numbersEvents.forEach {
            fooList.add(fooEventsList[it])
        }
        return fooList
    }

    private val fooEventsList = listOf(
        Event(title = "Благотворительный фонд Алины Кактотамовны"),
        Event(title = "\"Во имя жизни \""),
        Event(title = "Благотворительный фонд В. Потанина"),
        Event(title = "\"Детские домики \""),
        Event(title = "\"Мозайка счастья \""),
    )
}
