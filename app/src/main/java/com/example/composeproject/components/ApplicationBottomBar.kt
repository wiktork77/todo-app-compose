package com.example.composeproject.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composeproject.navigation.BaseDestinations

@Composable
fun ApplicationBottomBar(
    baseDestinations: List<BaseDestinations>,
    modifier: Modifier = Modifier,
    onDestinationSelect: (BaseDestinations) -> Unit,
    currentBaseScreen: String
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        baseDestinations.forEach { destination ->
            NavigationBarItem(
                selected = currentBaseScreen == destination.route,
                onClick = {onDestinationSelect(destination)},
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}