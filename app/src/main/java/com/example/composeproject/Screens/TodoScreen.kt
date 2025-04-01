package com.example.composeproject.Screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeproject.components.TodoAddFab
import com.example.composeproject.components.TodoModel
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.TodoRepository
import com.example.composeproject.data.vms.TodosViewModel

@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    onItemClick: (DBTodo) -> Unit = {},
) {
    val context = LocalContext.current
    val todosViewModel = TodosViewModel(TodoRepository(context))
    val items = todosViewModel.todos
    val showDialog = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf<DBTodo?>(null) }

    if (showDialog.value) {
        AlertDialogExample(
            onDismissRequest = {
                showDialog.value = false
                selectedItem.value = null
            },
            onConfirmation = {
                todosViewModel.removeItem(selectedItem.value!!)
                selectedItem.value = null
                showDialog.value = false
            },
            dialogTitle = "Delete",
            dialogText = "Do you really want to delete?",
            icon = Icons.Filled.Warning
        )
    }
    
    
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = 10.dp,
            bottom = 10.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items) {item ->
            TodoModel(
                dbModel = item,
                onclickBehavior = {
                    onItemClick(item)
                    println("i clicked")
                },
                onLongClickBehavior = {
                    showDialog.value = true
                    selectedItem.value = item
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    TodoScreen(
        onItemClick = {item ->
            println(item)
        }
    )
}


@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = dialogText)
            }

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                },
            ){
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}
