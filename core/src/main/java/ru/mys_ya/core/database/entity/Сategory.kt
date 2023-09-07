package ru.mys_ya.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("title")val title: String,
    @ColumnInfo("icon_url") val iconUrl: String
)
