package com.example.composeproject.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeproject.Screens.SettingText
import com.example.composeproject.data.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(
    modifier: Modifier = Modifier,
    style: TextStyle,
    title: String,
    tfValue: String,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = 50.dp)
    ) {
        SettingText(
            style = style,
            title = title,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        TextField(
            value = tfValue,
            onValueChange = {str ->
                onValueChange(str)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Transparent,
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, CircleShape),
            shape = CircleShape,
            textStyle = MaterialTheme.typography.bodyLarge,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiceInput(
    value: String,
    leadingIcon: @Composable () -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        leadingIcon = {leadingIcon()},
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, CircleShape),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Transparent,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        shape = CircleShape,
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputSpinner(
    title: String,
    options: List<String>,
    selectedText: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(horizontal = 50.dp)
    ) {
        SettingText(
            style = MaterialTheme.typography.bodyMedium,
            title = title,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = { value -> println(value) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray, CircleShape)
                    .padding(start = 25.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                shape = CircleShape,
                textStyle = MaterialTheme.typography.bodyLarge,
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        },
                        modifier = Modifier.background(Color.White)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionInput(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = {str ->
            onValueChange(str)
        },
        readOnly = false,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Transparent,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}


//@Preview(showBackground = true)
//@Composable
//fun UserInputSpinnerPreview() {
//    val categories = Category.values().toList()
//    val categoriesStrings = mutableListOf<String>()
//    categories.forEach { cat -> categoriesStrings.add(cat.toString())  }
//    UserInputSpinner(
//        options = categoriesStrings,
//        title = "Category"
//    )
//}