package com.joao.climanotes.database

import android.content.Context
import androidx.room.Room

object DatabaseInstance {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "climanotes.db"
            )
                .fallbackToDestructiveMigration() // destrói e recria o DB se mudar a versão
                .build()
            INSTANCE = instance
            instance
        }
    }
}
