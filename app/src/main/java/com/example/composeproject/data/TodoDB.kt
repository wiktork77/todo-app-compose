package com.example.composeproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DBTodo::class], version = 1)
abstract class TodoDB: RoomDatabase() {
    abstract fun myDao(): TodoDAO?

    companion object {
        private var DB_INSTANCE: TodoDB? = null

        @Synchronized
        open fun getDatabase(context: Context): TodoDB? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(context.applicationContext
                    ,TodoDB::class.java, "todo_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE
        }
    }
}