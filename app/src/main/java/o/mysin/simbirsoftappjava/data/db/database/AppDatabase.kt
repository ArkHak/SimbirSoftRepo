package o.mysin.simbirsoftappjava.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import o.mysin.simbirsoftappjava.data.db.converters.ListIntConverter
import o.mysin.simbirsoftappjava.data.db.converters.ListStringConverter
import o.mysin.simbirsoftappjava.data.db.dao.CategoryDao
import o.mysin.simbirsoftappjava.data.db.dao.EventDao
import o.mysin.simbirsoftappjava.data.db.entity.Category
import o.mysin.simbirsoftappjava.data.db.entity.Event

@Database(entities = [Category::class, Event::class], version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class, ListIntConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
