package com.example.composeproject.data

import android.content.Context
import java.time.LocalDate

class TodoRepository(context: Context) {
    private var dataList: MutableList<DBTodo>? = null
    private var myDao: TodoDAO
    private var db: TodoDB


    fun getData(): MutableList<DBTodo> {
        dataList = myDao.getAllData()
        return dataList as MutableList<DBTodo>
    }

    fun addItem(item: DBTodo): Boolean {
        return myDao.insert(item) > 0
    }

    fun deleteItem(item: DBTodo): Boolean {
        return myDao.delete(item) > 0
    }

    fun updateItem(item: DBTodo): Boolean {
        return myDao.update(item) > 0
    }

    fun getItemById(id: Int): DBTodo {
        return myDao.getById(id.toString())
    }



    companion object {
        private var R_INSTANCE: TodoRepository? = null
        fun getInstance(context: Context): TodoRepository {
            if (R_INSTANCE == null) {
                R_INSTANCE = TodoRepository(context)
            }
            return R_INSTANCE as TodoRepository
        }

        fun generateSampleTodos(): List<DBTodo> {
            val todoList = mutableListOf<DBTodo>()

            repeat(30) {
                val title = "Task ${it + 1}"
                val subTitle = "Subtask description for Task ${it + 1}"
                val category = Category.values().random()
                val description = "Detailed description for Task ${it + 1}"
                val dueTo = LocalDate.now().plusDays((1..30).random().toLong()).toString()
                val importance = (1..5).random()
                val paid = listOf(true, false).random()

                val todo = DBTodo(title, subTitle, category, description, dueTo, importance, paid)
                todoList.add(todo)
            }

            return todoList
        }
    }

    init {
        db = TodoDB.getDatabase(context)!!
        myDao = db.myDao()!!
    }
}