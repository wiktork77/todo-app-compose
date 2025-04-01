package com.example.composeproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo ORDER BY id ASC")
    fun getAllData(): MutableList<DBTodo>
    @Query("DELETE FROM todo")
    fun deleteAll()
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todo: DBTodo): Long
    @Delete
    fun delete(todo: DBTodo): Int
    @Update
    fun update(todo: DBTodo): Int
    @Query("SELECT * FROM todo WHERE id=:id")
    fun getById(id: String): DBTodo
}