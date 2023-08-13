package o.mysin.simbirsoftappjava.domain.model

import com.google.gson.annotations.SerializedName

data class HelpCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("image")
    val iconUrl: String,
)
