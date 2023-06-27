package o.mysin.simbirsoftappjava.ui.help

import androidx.lifecycle.ViewModel
import o.mysin.simbirsoftappjava.R
import o.mysin.simbirsoftappjava.data.HelpCategory

class HelpViewModel : ViewModel() {

    fun loadHelpCategory(): List<HelpCategory> {
        return listOf(
            HelpCategory(title = "Дети", icon = R.mipmap.help_children),
            HelpCategory(title = "Взрослые", icon = R.mipmap.help_adults),
            HelpCategory(title = "Пожилые", icon = R.mipmap.help_old_aged),
            HelpCategory(title = "Животные", icon = R.mipmap.help_animals),
            HelpCategory(title = "Мероприятия", icon = R.mipmap.help_events),
        )
    }
}
