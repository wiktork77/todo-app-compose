package com.example.composeproject.data.parsers

import androidx.compose.ui.text.toUpperCase
import com.example.composeproject.data.Category

class StringToCategoryParser {
    companion object {
        fun parse(value: String): Category {
            return when (value.uppercase()) {
                "CAR" -> Category.CAR
                "LAUNDRY" -> Category.LAUNDRY
                "GROCERIES" -> Category.GROCERIES
                "CLEANING" -> Category.CLEANING
                "EXERCISE" -> Category.EXERCISE
                "MEETING" -> Category.MEETING
                "OTHER" -> Category.OTHER
                else -> Category.OTHER
            }
        }
    }
}