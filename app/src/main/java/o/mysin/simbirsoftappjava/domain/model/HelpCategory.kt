package o.mysin.simbirsoftappjava.domain.model

import com.google.gson.annotations.SerializedName

data class HelpCategory(
    val id: Int,
    val title: String,
    @SerializedName("icon_url") val iconUrl: Int,
    val isEnabled: Boolean = true,
)
