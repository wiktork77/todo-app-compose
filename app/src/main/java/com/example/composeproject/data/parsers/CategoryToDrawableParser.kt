package com.example.composeproject.data.parsers

import com.example.composeproject.R
import com.example.composeproject.data.Category

class CategoryToDrawableParser {
    companion object {
        fun parse(category: Category): Int {
            var resource = when (category) {
                Category.CAR -> {
                    R.drawable.ic_car_image
                }

                Category.LAUNDRY -> {
                    R.drawable.ic_laundry_image
                }

                Category.GROCERIES -> {
                    R.drawable.ic_groceries_image
                }

                Category.CLEANING -> {
                    R.drawable.ic_cleaning_image
                }

                Category.EXERCISE -> {
                    R.drawable.ic_excercise_image
                }

                Category.MEETING -> {
                    R.drawable.ic_meeting_image
                }

                Category.OTHER -> {
                    R.drawable.ic_other_image
                }
            }
            return resource
        }
    }
}