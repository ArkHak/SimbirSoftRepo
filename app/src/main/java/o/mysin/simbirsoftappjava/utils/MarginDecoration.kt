package o.mysin.simbirsoftappjava.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import o.mysin.simbirsoftappjava.R

class MarginDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val margin = context.resources.getDimensionPixelSize( ru.mys_ya.core.R.dimen.item_helps_margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1
        val column = position % spanCount

        outRect.left = margin - column * margin / spanCount
        outRect.right = (column + 1) * margin / spanCount

        outRect.bottom = margin
    }
}

class NewsItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val margin = context.resources.getDimensionPixelSize( ru.mys_ya.core.R.dimen.margin_bottom_last_item)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)


        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0
        if (position == itemCount - 1) {
            outRect.bottom = margin
        }
    }


}
