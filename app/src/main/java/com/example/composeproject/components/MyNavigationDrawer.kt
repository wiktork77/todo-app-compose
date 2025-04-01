package com.example.composeproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeproject.R
import com.example.composeproject.navigation.BaseDestinations
import kotlinx.coroutines.launch


@Composable
fun NavDrawerHeader(
    name: String,
    avatarId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 15.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = avatarId),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 25.dp),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun NavDrawerContent() {
    
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationDrawer(
    drawerState: DrawerState,
    avatarId: Int,
    name: String,
    destinations: List<BaseDestinations>,
    onDestinationSelect: (BaseDestinations) -> Unit,
    currentBaseScreen: String,
    content: @Composable () -> Unit = {}
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                NavDrawerHeader(name = name, avatarId = avatarId)
                Divider()
                destinations.forEach { destination ->
                    NavigationDrawerItem(
                        selected = currentBaseScreen == destination.route,
                        onClick = {
                            onDestinationSelect(destination)
                        },
                        label = {
                            Text(text = destination.title)
                        }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun NavDrawerHeaderPreview() {
    NavDrawerHeader(name = "Wiktor", avatarId = R.drawable.ic_other_image)
}