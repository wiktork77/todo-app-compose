package com.example.composeproject.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeproject.Screens.SettingText
import com.example.composeproject.Screens.ShowDatePicker

@Composable
fun LabeledItem(
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        SettingText(
            style = MaterialTheme.typography.bodyMedium,
            title = label,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        content()
    }
}