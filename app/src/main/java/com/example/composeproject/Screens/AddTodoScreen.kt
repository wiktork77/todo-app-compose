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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeproject.components.DescriptionInput
import com.example.composeproject.components.LabeledItem
import com.example.composeproject.components.NiceInput
import com.example.composeproject.components.UserInput
import com.example.composeproject.components.UserInputSpinner
import com.example.composeproject.data.Category
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.vms.AddTodoViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    modifier: Modifier = Modifier,
    addTodoViewModel: AddTodoViewModel = viewModel(),
    onAdd: (DBTodo) -> Unit,
    onCancel: () -> Unit
) {
    val categories = Category.values().toList()
    val categoriesStrings = mutableListOf<String>()
    val localContext = LocalContext.current
    categories.forEach { cat -> categoriesStrings.add(cat.toString()) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        UserInput(
            style = MaterialTheme.typography.bodyMedium,
            title = "Title",
            tfValue = addTodoViewModel.titleValue.value
        ) {str -> addTodoViewModel.setTitleValue(str)
        }
        UserInput(
            style = MaterialTheme.typography.bodyMedium,
            title = "Sub title",
            tfValue = addTodoViewModel.subtitleValue.value
        ) { str ->
            addTodoViewModel.setSubtitleValue(str)
        }
        UserInputSpinner(
            options = categoriesStrings,
            title = "Category",
            selectedText = addTodoViewModel.selectedCategory.value
        ) { str ->
            addTodoViewModel.setSelectedCategory(str)
        }
        LabeledItem(label = "Due to") {
            NiceInput(
                value = addTodoViewModel.currentDate.value,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            ShowDatePicker(localContext, addTodoViewModel)
                        }
                    )
                }
            )
        }
        LabeledItem(label = "Importance") {
            Slider(value = addTodoViewModel.sliderValue.value,
                valueRange = 1f..5f,
                steps = 3,
                onValueChange = {value ->
                    addTodoViewModel.setSliderValue(value)
                }
            )
        }
        LabeledItem(label = "Is paid?") {
            Switch(
                checked = addTodoViewModel.isPaidValue.value,
                onCheckedChange = {checked ->
                    addTodoViewModel.setIsPaidValue(checked)
                }
            )
        }
        LabeledItem(label = "Description") {
            DescriptionInput(
                value = addTodoViewModel.descriptionValue.value,
                modifier = Modifier.height(150.dp)
            ) { str ->
                addTodoViewModel.setDescriptionValue(str)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val item = addTodoViewModel.prepareItem()
                    if (item != null) {
                        onAdd(item)
                    } else {
                        val toast = Toast.makeText(localContext, "You need to fill all inputs!", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                },
                modifier = Modifier.weight(1f)
                    .padding(end = 10.dp)
            ) {
                Text(text = "Add")
            }
            Button(
                onClick = {
                    onCancel()
                },
                modifier = Modifier.weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(text = "Cancel")
            }
        }
    }
}


fun ShowDatePicker(context: Context, ASH: AddTodoViewModel) {

    var year: Int
    var month: Int
    var day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)


    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            var yearMod = "$year"
            var monthMod = "${month + 1}"
            var dayMod = "$dayOfMonth"
            if (month + 1 < 10) {
                monthMod = "0${month + 1}"
            }
            if (dayOfMonth < 10) {
                dayMod = "0$dayOfMonth"
            }
            ASH.setCurrentDate("$dayMod-$monthMod-$yearMod")
            println(ASH.currentDate.value)
        }, year, month, day
    )
    datePickerDialog.show()
}


//@Preview(showBackground = true)
//@Composable
//fun AddTodoScreenPreview() {
//    AddTodoScreen()
//}