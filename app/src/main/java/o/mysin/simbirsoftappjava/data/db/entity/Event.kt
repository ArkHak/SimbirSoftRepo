package o.mysin.simbirsoftappjava.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class Event(
    @PrimaryKey
    val id: Int,
    val organisation: String,
    val address: String,
    val phone: String,
    val photos: List<String>,
    val name: String,
    val description: String,
    @ColumnInfo("full_description") val fullDescription: String,
    @ColumnInfo("start_date") val startDate: Long,
    @ColumnInfo("end_date") val endDate: Long,
    val categories: List<Int>,
)
