package com.joao.climanotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joao.climanotes.database.dao.CategoryDao
import com.joao.climanotes.database.dao.NoteDao
import com.joao.climanotes.model.Category
import com.joao.climanotes.model.Note

@Database(entities = [Category::class, Note::class], version = 3) // versão aumentada
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun noteDao(): NoteDao
}

