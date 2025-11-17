package com.joao.climanotes.database.dao

import androidx.room.*
import com.joao.climanotes.model.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Update
    suspend fun update(category: Category)    // 👈 necessário para edição

    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<Category>
}
