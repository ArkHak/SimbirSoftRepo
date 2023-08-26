package ru.mys_ya.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mys_ya.core.data.db.entity.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<Event>)

    @Query("SELECT * FROM event")
    suspend fun getAllEvents(): List<Event>

    @Query("SELECT * FROM event WHERE id = :id")
    suspend fun getEvent(id: Int): Event
}
