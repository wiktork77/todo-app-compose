package com.example.composeproject.stateholders

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.composeproject.R

class SettingsScreenStateHolder(context: Context) {
    val sp = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    val name = mutableStateOf(sp.getString("name", ""))
    val age = mutableStateOf(sp.getString("age", ""))
    val avatarId = mutableStateOf(sp.getInt("avatarId", R.drawable.ic_other_image))
    val info = mutableStateOf(sp.getString("info", ""))

    fun setName(value: String) {
        val spEdit = sp.edit()
        name.value = value
        spEdit.putString("name", value)
        spEdit.apply()
    }

    fun setAge(value: String) {
        val spEdit = sp.edit()
        age.value = value
        spEdit.putString("age", value)
        spEdit.apply()
    }

    fun setAvatarId(value: Int) {
        println("setting avar to $value")
        val spEdit = sp.edit()
        avatarId.value = value
        spEdit.putInt("avatarId", value)
        spEdit.apply()
    }

    fun setInfo(value: String) {
        val spEdit = sp.edit()
        info.value = value
        spEdit.putString("info", value)
        spEdit.apply()
    }

}