package com.example.composeproject.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoAddFab(
    onClick: () -> Unit = { print("Todo add clicked") },
    displayState: Boolean
) {
    if (displayState) {
        FloatingActionButton(
            onClick = { onClick() },
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add fab"
            )
        }
    }
}

