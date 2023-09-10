package ru.mys_ya.feature_help_api.model

import com.google.gson.annotations.SerializedName

data class HelpCategory(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("image") val iconUrl: String,
)
