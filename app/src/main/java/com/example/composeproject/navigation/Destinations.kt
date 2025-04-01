package com.example.composeproject.navigation

import android.view.WindowInsets.Side
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument


interface TopBarDisplayable {
    val title: String
}

interface Destinations: TopBarDisplayable {
    val route: String
}

interface BaseDestinations: Destinations {
    val icon: ImageVector
}

object HomeScreen: BaseDestinations {
    override val route = "home"
    override val icon: ImageVector = Icons.Default.Home
    override val title = "Home screen"
}

object Settings: BaseDestinations {
    override val route = "settings"
    override val icon: ImageVector = Icons.Default.AccountCircle
    override val title = "Settings"
}

object Todos: BaseDestinations {
    override val route = "todos"
    override val icon: ImageVector = Icons.Default.List
    override val title = "Todos"
}

val baseDestinations: List<BaseDestinations> = listOf(Settings, HomeScreen, Todos)


interface SideDestinations: Destinations {
}

object AddTodo: SideDestinations {
    override val route = "todos/add"
    override val title = "Add todo"
}

object HybridScreenDest: SideDestinations {
    override val route = "hybrid"
    override val title = "Todo"
    const val selectedTodoArg = "selectedTodo"
    const val modeArg = "mode"
    val routeWithArgs = "${route}/{${selectedTodoArg}}/{${modeArg}}"
    val arguments = listOf(
        navArgument(selectedTodoArg) {type = NavType.IntType},
        navArgument(modeArg) {type = NavType.StringType}
    )
}

