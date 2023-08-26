package ru.mys_ya.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class News(
    @SerializedName("id")
    val id: Int,
    @SerializedName("organisation")
    val organisation: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("photos")
    val photos: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fullDescription")
    val fullDescription: String,
    @SerializedName("startDate")
    val startDate: Long,
    @SerializedName("endDate")
    val endDate: Long,
    @SerializedName("categories")
    val categories: List<Int>,
) : Parcelable
