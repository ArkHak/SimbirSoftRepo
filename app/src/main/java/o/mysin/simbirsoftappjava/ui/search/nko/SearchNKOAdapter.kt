package o.mysin.simbirsoftappjava.ui.search.nko

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.domain.model.Event
import o.mysin.simbirsoftappjava.databinding.ItemSearchResultBinding

class SearchNKOAdapter : RecyclerView.Adapter<SearchNKOViewHolder>() {

    private var _eventList: List<Event> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNKOViewHolder {
        val binding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return SearchNKOViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _eventList.size
    }

    override fun onBindViewHolder(holder: SearchNKOViewHolder, position: Int) {
        holder.bind(_eventList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateEventList(eventCategoryList: List<Event>) {
        _eventList = eventCategoryList
        notifyDataSetChanged()
    }
}
