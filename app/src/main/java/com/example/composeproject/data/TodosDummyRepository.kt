package com.example.composeproject.data

class TodosDummyRepository {
    companion object {
        private val todos: List<Int> = List(30) {i -> i}
        fun getTodos(): List<Int> {
            return todos;
        }
    }
}