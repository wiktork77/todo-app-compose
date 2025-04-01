package com.example.composeproject.data

import com.example.composeproject.R

class AvatarsRepository {
    companion object {
        private val avatars: List<Int> = listOf(
            R.drawable.image_ape,
            R.drawable.image_castle,
            R.drawable.image_cat,
            R.drawable.image_flower,
            R.drawable.image_orang,
            R.drawable.image_rails,
            R.drawable.image_rock,
            R.drawable.image_sun,
            R.drawable.image_wood
        )
        fun getAvatars(): List<Int> {
            return avatars
        }
    }
}