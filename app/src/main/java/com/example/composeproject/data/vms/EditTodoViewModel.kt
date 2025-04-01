package com.example.composeproject.data.vms

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.parsers.StringToCategoryParser

class EditTodoViewModel(): ViewModel() {
    val titleValue = mutableStateOf("")
    val subtitleValue = mutableStateOf("")
    val currentDate = mutableStateOf("")
    val sliderValue = mutableStateOf(1f)
    val isPaidValue = mutableStateOf(false)
    val descriptionValue = mutableStateOf("")
    val selectedCategory = mutableStateOf("")
    var setup = false

    fun setValuesBySubject(subject: DBTodo) {
        titleValue.value = subject.title
        subtitleValue.value = subject.subTitle
        currentDate.value = subject.dueTo
        sliderValue.value = subject.importance.toFloat()
        isPaidValue.value = subject.paid
        descriptionValue.value = subject.description
        selectedCategory.value = subject.category.toString()
        setup = true
    }

    fun setTitleValue(value: String) {
        titleValue.value = value
    }

    fun setSubtitleValue(value: String) {
        subtitleValue.value = value
    }

    fun setCurrentDate(date: String) {
        currentDate.value = date
    }

    fun setSliderValue(value: Float) {
        sliderValue.value = value
    }

    fun setIsPaidValue(value: Boolean) {
        isPaidValue.value = value
    }

    fun setDescriptionValue(value: String) {
        descriptionValue.value = value
    }

    fun setSelectedCategory(value: String) {
        selectedCategory.value = value
    }

    fun prepareItem(): DBTodo? {
        val item = DBTodo(
            titleValue.value,
            subtitleValue.value,
            StringToCategoryParser.parse(selectedCategory.value),
            descriptionValue.value,
            currentDate.value,
            sliderValue.value.toInt(),
            isPaidValue.value
        )
        if (item.isValid()) {
            return item
        } else {
            return null
        }
    }
}