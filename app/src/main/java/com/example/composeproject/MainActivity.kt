package com.example.composeproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeproject.components.ApplicationBottomBar
import com.example.composeproject.components.ApplicationTopBar
import com.example.composeproject.Screens.MainScreen
import com.example.composeproject.Screens.SettingsScreen
import com.example.composeproject.Screens.TodoScreen
import com.example.composeproject.components.MyNavigationDrawer
import com.example.composeproject.components.TodoAddFab
import com.example.composeproject.data.Category
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.TodoRepository
import com.example.composeproject.navigation.AddTodo
import com.example.composeproject.navigation.AppNavHost
import com.example.composeproject.navigation.BaseDestinations
import com.example.composeproject.navigation.Destinations
import com.example.composeproject.navigation.Todos
import com.example.composeproject.navigation.baseDestinations
import com.example.composeproject.stateholders.AppScreenStateHolder
import com.example.composeproject.ui.theme.ComposeProjectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                MyApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val appStateHolder = AppScreenStateHolder.getInstance(LocalContext.current)
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    MyNavigationDrawer(
        drawerState = drawerState,
        avatarId = appStateHolder.avatarId.value,
        destinations = baseDestinations,
        onDestinationSelect = {
                destination -> navController.navigateSingleTopTo(destination, appStateHolder)
            scope.launch {
                drawerState.apply {
                    close()
                }
            }
        },
        currentBaseScreen = appStateHolder.currentBaseScreen.value,
        name = appStateHolder.name.value!!
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            floatingActionButton = { TodoAddFab(
                onClick = {
                    navController.navigateSingleTopTo(AddTodo, appStateHolder)
                },
                displayState = appStateHolder.showFab.value
            ) },
            topBar = {
                ApplicationTopBar(
                    title = appStateHolder.currentTopBarTitle.value,
                    onInfoClicked = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "You can edit your data in edit tab and save your todos in todo tab!",
                                actionLabel = "OK",
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                ) {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            },
            bottomBar = {
                ApplicationBottomBar(
                    baseDestinations = baseDestinations,
                    onDestinationSelect = {
                            destination -> navController.navigateSingleTopTo(destination, appStateHolder)
                    },
                    currentBaseScreen = appStateHolder.currentBaseScreen.value
                )
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                appStateHolder = appStateHolder
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ComposeProjectTheme {
        MyApp()
    }
}

fun NavHostController.navigateSingleTopTo(destination: Destinations, appStateHolder: AppScreenStateHolder) {
    if (destination.route == Todos.route) {
        appStateHolder.setFabState(true)
    } else {
        appStateHolder.setFabState(false)
    }
    if (destination is BaseDestinations) {
        appStateHolder.setCurrentBaseScreen(destination.route)
        appStateHolder.setCurrentTopBarTitle(destination.title)
    }

    println("nav triggered")
    this.navigate(destination.route) {launchSingleTop = true}
}
