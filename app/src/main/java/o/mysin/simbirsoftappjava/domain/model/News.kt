package o.mysin.simbirsoftappjava.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    @SerializedName("id")
    val id: Int,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("owner_address")
    val ownerAddress: String,
    @SerializedName("owner_contacts")
    val ownerContacts: String,
    @SerializedName("pictures_url")
    val picturesUrl: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("full_description")
    val fullDescription: String,
    @SerializedName("start_date_time")
    val startDateTime: Long,
    @SerializedName("end_date_time")
    val endDateTime: Long,
    @SerializedName("list_help_category_id")
    val listHelpCategoryId: List<Int>,
    @SerializedName("is_viewed")
    var isViewed: Boolean = false,
) : Parcelable
