package o.mysin.simbirsoftappjava.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import o.mysin.simbirsoftappjava.R

class MarginDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val margin: Int

    init {
        margin = context.resources.getDimensionPixelSize(R.dimen.item_helps_margin)
    }

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

        if (position < spanCount) {
            outRect.top = margin
        }
        outRect.bottom = margin
    }
}

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    private val MIN_SCALE = 0.85f
    private val MIN_ALPHA = 0.5f

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    alpha = (MIN_ALPHA +
                        (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    alpha = 0f
                }
            }
        }
    }
}
