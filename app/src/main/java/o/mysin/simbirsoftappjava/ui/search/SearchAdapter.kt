package o.mysin.simbirsoftappjava.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.data.Event
import o.mysin.simbirsoftappjava.databinding.ItemSearchResultBinding

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private var eventList: List<Event> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateEventList(newEventCategoryList: List<Event>) {
        eventList = newEventCategoryList
        notifyDataSetChanged()
    }
}
