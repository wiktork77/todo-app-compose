package com.example.composeproject.data.parsers

import com.example.composeproject.R

class ImportanceToDrawableParser {
    companion object {
        fun parse(importance: Int): Int {
            var resource = R.drawable.ic_not_urgent_todo

            if (importance in 3..4) {
                resource = R.drawable.ic_normal_todo
            } else if (importance == 5) {
                resource = R.drawable.ic_urgent_todo
            }

            return resource
        }
    }
}