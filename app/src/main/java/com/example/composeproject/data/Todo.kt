package com.example.composeproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class DBTodo(
    var title: String,
    var subTitle: String,
    var category: Category,
    var description: String,
    var dueTo: String,
    var importance: Int,
    var paid: Boolean
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    fun isValid(): Boolean {
        return !title.isEmpty() && !subTitle.isEmpty() && !description.isEmpty()
                && !dueTo.isEmpty()
    }

    fun changeTo(item: DBTodo) {
        title = item.title
        subTitle = item.subTitle
        category = item.category
        description = item.description
        dueTo = item.dueTo
        importance = item.importance
        paid = item.paid
    }

    companion object {
        fun getSampleTodo(): DBTodo {
            return DBTodo(
                ("Kot").repeat(30),
                ("kotek").repeat(20),
                Category.OTHER,
                "desc",
                "2022-12-12",
                1,
                false
            )
        }
    }
}


enum class Category {
    CAR,
    LAUNDRY,
    GROCERIES,
    CLEANING,
    EXERCISE,
    MEETING,
    OTHER
}