package o.mysin.simbirsoftappjava.data.db.converters

import androidx.room.TypeConverter

class ListIntConverter {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return value.split(", ").map { it.toInt() }
    }

    @TypeConverter
    fun toString(list: List<Int>): String {
        return list.joinToString(", ")
    }
}
