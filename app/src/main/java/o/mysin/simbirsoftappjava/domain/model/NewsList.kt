package o.mysin.simbirsoftappjava.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsList(
    val newsList: List<News>,
) : Parcelable
