package o.mysin.simbirsoftappjava.domain.model

import com.google.gson.annotations.SerializedName

data class HelpCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("icon_url")
    val iconUrl: Int,
    @SerializedName("is_enabled")
    val isEnabled: Boolean = true,
)
