package com.example.composeproject.stateholders

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.composeproject.R
import com.example.composeproject.navigation.BaseDestinations
import com.example.composeproject.navigation.HomeScreen

class AppScreenStateHolder(context: Context) {
    var showFab = mutableStateOf(false)
    var currentBaseScreen = mutableStateOf(HomeScreen.route)
    var currentTopBarTitle = mutableStateOf(HomeScreen.title)


    val sp = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    val name = mutableStateOf(sp.getString("name", ""))
    val age = mutableStateOf(sp.getString("age", ""))
    val avatarId = mutableStateOf(sp.getInt("avatarId", R.drawable.ic_other_image))
    val info = mutableStateOf(sp.getString("info", ""))


    fun setFabState(state: Boolean) {
        showFab.value = state
        println("fab: $state")
    }

    fun setCurrentBaseScreen(screenRoute: String) {
        currentBaseScreen.value = screenRoute
    }

    fun setCurrentTopBarTitle(title: String) {
        currentTopBarTitle.value = title
        println("changing to $title")
        println(currentTopBarTitle)
        println(currentBaseScreen)
    }


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

    fun setSomething(value: String, tag: String) {
        when (tag.lowercase()) {
            "name" -> setName(value)
            "age" -> setAge(value)
            "info" -> setInfo(value)
        }
    }

    companion object {
        var instance: AppScreenStateHolder? = null
        fun getInstance(context: Context): AppScreenStateHolder {
            if (instance == null) {
                instance = AppScreenStateHolder(context)
            }
            return instance!!
        }
    }
}