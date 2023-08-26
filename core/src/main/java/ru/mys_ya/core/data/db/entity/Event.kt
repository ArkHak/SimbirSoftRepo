package ru.mys_ya.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class Event(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("organisation") val organisation: String,
    @ColumnInfo("address") val address: String,
    @ColumnInfo("phone") val phone: String,
    @ColumnInfo("photos") val photos: List<String>,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("full_description") val fullDescription: String,
    @ColumnInfo("start_date") val startDate: Long,
    @ColumnInfo("end_date") val endDate: Long,
    @ColumnInfo("categories") val categories: List<Int>,
)
