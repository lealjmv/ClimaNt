package com.joao.climanotes.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import com.joao.climanotes.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)    // <-- ADICIONADO

    @Query("SELECT * FROM notes WHERE categoryId = :catId")
    suspend fun getNotesByCategory(catId: Int): List<Note>

    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<Note>
}
