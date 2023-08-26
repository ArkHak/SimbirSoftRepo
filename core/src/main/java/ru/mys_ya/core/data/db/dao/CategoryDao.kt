package ru.mys_ya.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mys_ya.core.data.db.entity.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Category>)

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<Category>
}
