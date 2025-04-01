package com.example.composeproject.data.vms

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.TodoRepository

class TodosViewModel(repository: TodoRepository): ViewModel() {
    private val repo = repository
    private val _todos = repository.getData().toMutableStateList()
    val todos: List<DBTodo>
        get() = _todos

    fun addItem(dbTodo: DBTodo) {
        repo.addItem(dbTodo)
        _todos.add(dbTodo)
    }
    fun removeItem(dbTodo: DBTodo) {
        repo.deleteItem(dbTodo)
        _todos.remove(dbTodo)
    }

    fun getById(id: Int): DBTodo {
        return repo.getItemById(id)
    }
}