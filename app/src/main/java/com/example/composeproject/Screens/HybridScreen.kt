package com.example.composeproject.Screens

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeproject.components.BaseInput
import com.example.composeproject.components.DescriptionHybrid
import com.example.composeproject.components.LabeledItem
import com.example.composeproject.components.UserInputSpinner
import com.example.composeproject.data.Category
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.TodoRepository
import com.example.composeproject.data.vms.AddTodoViewModel
import com.example.composeproject.data.vms.EditTodoViewModel
import com.example.composeproject.data.vms.TodosViewModel
import java.util.Calendar


@Composable
fun HybridScreen(
    todoId: Int,
    mode: String,
    onModifyPressed: () -> Unit = {},
    onBackPressed: () -> Unit = {},
    onCancelPressed: () -> Unit = {},
    onSavePressed: () -> Unit = {}
) {
    val context = LocalContext.current
    val repo = TodoRepository.getInstance(context)
    val item = repo.getItemById(todoId)
    if (mode.lowercase() == "read") {
        ReadScreen(
            item = item,
            onModifyPressed = onModifyPressed,
            onBackPressed = onBackPressed,
        )
    } else if (mode.lowercase() == "edit") {
        EditScreen(
            item = item,
            onCancelPressed = onCancelPressed,
            onSavePressed = onSavePressed
        )
    }
}

@Composable
fun ReadScreen(
    item: DBTodo,
    onModifyPressed: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val readOnly = true
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)

    ) {
        LabeledItem(label = "Title") {
            BaseInput(
                value = item.title,
                readOnly = readOnly,
                onValueChanged = {}
            )
        }
        LabeledItem(label = "Sub title") {
            BaseInput(
                value = item.subTitle,
                readOnly = readOnly,
                onValueChanged = {}
            )
        }
        LabeledItem(label = "Category") {
            BaseInput(
                value = item.category.toString(),
                readOnly = readOnly,
                onValueChanged = {})
        }
        LabeledItem(label = "Due to") {
            BaseInput(
                value = item.dueTo,
                readOnly = readOnly,
                onValueChanged = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null
                    )
                }
            )
        }
        LabeledItem(label = "Importance") {
            Slider(value = item.importance.toFloat(),
                valueRange = 1f..5f,
                steps = 3,
                onValueChange = {}
            )
        }
        LabeledItem(label = "Is paid?") {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = item.paid,
                    onCheckedChange = {}
                )
                Text(
                    text = if (item.paid) {
                        "Yes"
                    } else {
                        "No"
                    },
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
        LabeledItem(label = "Description") {
            DescriptionHybrid(
                value = item.description,
                readOnly = readOnly,
                modifier = Modifier,
                onValueChange = {}
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                onModifyPressed()
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)

            ) {
                Text(text = "Modify")
            }
            Button(onClick = {
                onBackPressed()
            },
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
            ) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
fun EditScreen(
    item: DBTodo,
    onSavePressed: () -> Unit = {},
    onCancelPressed: () -> Unit = {},
    onCalendarPressed: () -> Unit = {},
    editTodoViewModel: EditTodoViewModel = viewModel(),
) {
    if (!editTodoViewModel.setup) {
        editTodoViewModel.setValuesBySubject(item)
        println("init")
    }
    val readOnly = false
    val categories = Category.values().toList()
    val categoriesStrings = mutableListOf<String>()
    val context = LocalContext.current
    categories.forEach { cat -> categoriesStrings.add(cat.toString()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)

    ) {
        LabeledItem(label = "Title") {
            BaseInput(
                value = editTodoViewModel.titleValue.value,
                readOnly = readOnly,
                onValueChanged = {value ->
                    editTodoViewModel.setTitleValue(value)
                }
            )
        }
        LabeledItem(label = "Sub title") {
            BaseInput(
                value = editTodoViewModel.subtitleValue.value,
                readOnly = readOnly,
                onValueChanged = {value ->
                    editTodoViewModel.setSubtitleValue(value)
                }
            )
        }
        UserInputSpinner(
            options = categoriesStrings,
            title = "Category",
            selectedText = editTodoViewModel.selectedCategory.value
        ) { str ->
            editTodoViewModel.setSelectedCategory(str)
        }
        LabeledItem(label = "Due to") {
            BaseInput(
                value = editTodoViewModel.currentDate.value,
                readOnly = false,
                onValueChanged = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            ShowDatePicker(context, editTodoViewModel)
                        }
                    )
                }
            )
        }
        LabeledItem(label = "Importance") {
            Slider(value = editTodoViewModel.sliderValue.value.toFloat(),
                valueRange = 1f..5f,
                steps = 3,
                onValueChange = {value ->
                    editTodoViewModel.setSliderValue(value)
                }
            )
        }
        LabeledItem(label = "Is paid?") {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = editTodoViewModel.isPaidValue.value,
                    onCheckedChange = {isChecked ->
                        editTodoViewModel.setIsPaidValue(isChecked)
                    }
                )
            }
        }
        LabeledItem(label = "Description") {
            DescriptionHybrid(
                value = editTodoViewModel.descriptionValue.value,
                readOnly = readOnly,
                modifier = Modifier,
                onValueChange = {value ->
                    editTodoViewModel.setDescriptionValue(value)
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val repository: TodoRepository = TodoRepository.getInstance(context)
                val updatedItem = editTodoViewModel.prepareItem()
                if (updatedItem != null) {
                    item.changeTo(updatedItem)
                    repository.updateItem(item)
                    onSavePressed()
                } else {
                    val toast = Toast.makeText(context, "You need to fill all inputs!", Toast.LENGTH_SHORT)
                    toast.show()
                }
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
            ) {
                Text(text = "Save")
            }
            Button(onClick = {
                onCancelPressed()
            },
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
            ) {
                Text(text = "Cancel")
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun HybridScreenPreview() {
//    HybridScreen()
//}

//@Preview(showBackground = true)
//@Composable
//fun ReadScreenPreview() {
//    ReadScreen(DBTodo.getSampleTodo())
//}

fun ShowDatePicker(context: Context, etvm: EditTodoViewModel) {

    var year: Int
    var month: Int
    var day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)


    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            var yearMod = "$year"
            var monthMod = "${month + 1}"
            var dayMod = "$dayOfMonth"
            if (month + 1 < 10) {
                monthMod = "0${month + 1}"
            }
            if (dayOfMonth < 10) {
                dayMod = "0$dayOfMonth"
            }
            etvm.setCurrentDate("$dayMod-$monthMod-$yearMod")
            println(etvm.currentDate.value)
        }, year, month, day
    )
    datePickerDialog.show()
}