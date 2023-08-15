package o.mysin.simbirsoftappjava.data.db.converters

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(", ")
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return list.joinToString(", ")
    }
}