package com.example.composeproject.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseInput(
    value: String,
    readOnly: Boolean,
    onValueChanged: (String) -> Unit,
    leadingIcon: @Composable () -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = { str -> onValueChanged(str) },
        readOnly = readOnly,
        trailingIcon = {},
        leadingIcon = leadingIcon,
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
fun DescriptionHybrid(
    value: String,
    readOnly: Boolean,
    modifier: Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = {str ->
            onValueChange(str)
        },
        readOnly = readOnly,
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