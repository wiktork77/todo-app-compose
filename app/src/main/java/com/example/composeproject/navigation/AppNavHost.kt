package com.example.composeproject.navigation

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeproject.Screens.AddTodoScreen
import com.example.composeproject.Screens.HybridScreen
import com.example.composeproject.Screens.MainScreen
import com.example.composeproject.Screens.SettingsScreen
import com.example.composeproject.Screens.TodoScreen
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.TodoRepository
import com.example.composeproject.data.vms.TodosViewModel
import com.example.composeproject.navigateSingleTopTo
import com.example.composeproject.stateholders.AppScreenStateHolder


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    appStateHolder: AppScreenStateHolder
) {
    val todosViewModel = TodosViewModel(TodoRepository(LocalContext.current))
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route,
        modifier = modifier,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIn(
                animationSpec = tween(300, easing = EaseIn),
                initialOffset = { IntOffset.Zero }
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOut(
                animationSpec = tween(300, easing = EaseOut),
                targetOffset = { IntOffset.Zero }
            )
        }
    ) {
        composable(
            route = HomeScreen.route,
        ) {
            MainScreen()
        }
        composable(route = Settings.route) {
            SettingsScreen(
                avatarId = appStateHolder.avatarId.value,
                name = appStateHolder.name.value!!,
                age = appStateHolder.age.value!!,
                info = appStateHolder.info.value!!,
                onSlide = {avatarId -> appStateHolder.setAvatarId(avatarId)},
                onInputChange = {value, sptag -> appStateHolder.setSomething(value, sptag)}
            )
        }
        composable(route = Todos.route) {
            TodoScreen(
                onItemClick = {item ->
                    navController.navigate("${HybridScreenDest.route}/${item.id}/read")
                    appStateHolder.setCurrentTopBarTitle("Todo Description")
                    appStateHolder.setFabState(false)
                }
            )
        }
        composable(route = AddTodo.route) {
            AddTodoScreen(
                onAdd = {
                        todo ->
                    todosViewModel.addItem(todo)
                    navController.navigateSingleTopTo(Todos, appStateHolder)
                        },
                onCancel = {
                    navController.navigateSingleTopTo(Todos, appStateHolder)
                }
            )
        }

        composable(
            route = HybridScreenDest.routeWithArgs,
            arguments = HybridScreenDest.arguments
        ) { backstackEntry ->
            val todoId = backstackEntry.arguments?.getInt(HybridScreenDest.selectedTodoArg)
            val mode = backstackEntry.arguments?.getString(HybridScreenDest.modeArg)
            val onBackPressed = {
                navController.navigateSingleTopTo(Todos, appStateHolder)
            }
            val onCancelPressed = {
                navController.navigate("${HybridScreenDest.route}/${todoId}/read")
                appStateHolder.setCurrentTopBarTitle("Todo Description")
            }
            val onModifyPressed = {
                appStateHolder.setCurrentTopBarTitle("Todo Edit")
                navController.navigate("${HybridScreenDest.route}/${todoId}/edit")
            }
            val onSavePressed = {
                navController.navigateSingleTopTo(Todos, appStateHolder)
                appStateHolder.setCurrentTopBarTitle("Todo")
            }
            appStateHolder.setCurrentBaseScreen(HybridScreenDest.route)
            HybridScreen(
                todoId = todoId!!,
                mode = mode!!,
                onBackPressed = onBackPressed,
                onCancelPressed = onCancelPressed,
                onModifyPressed = onModifyPressed,
                onSavePressed = onSavePressed
            )
        }
    }
}