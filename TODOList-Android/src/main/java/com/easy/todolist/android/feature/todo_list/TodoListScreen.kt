package com.easy.todolist.android.feature.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.R
import com.easy.todolist.android.feature.todo_list.components.AddNewTaskSheet
import com.easy.todolist.android.feature.todo_list.components.TaskCardView
import com.easy.todolist.android.feature.todo_list.components.TaskFilterHeader
import com.easy.todolist.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    uiState: TodoListUIState,
    isAddNewTaskOpen: Boolean,
    newTask: Task?,
    onEvent: (TodoListEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        contentDescription = null,
                        painter = painterResource(id = R.drawable.ic_todo_list),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(onClick = {
                        onEvent(TodoListEvent.SettingClick)
                    }) {
                        Icon(
                            contentDescription = null,
                            painter = painterResource(id = R.drawable.ic_settings),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(TodoListEvent.AddNewTask)
            }, shape = MaterialTheme.shapes.extraLarge) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        if (isAddNewTaskOpen) {
            AddNewTaskSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                task = newTask,
                onEvent = onEvent
            )
        }
        when (uiState) {
            is TodoListUIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Loading...")
                }
            }

            is TodoListUIState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Oops, something went wrong!!!")
                }
            }

            is TodoListUIState.Success -> {
                if (uiState.tasks.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Empty View")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(it),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            TaskFilterHeader(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                tint = MaterialTheme.colorScheme.primary,
                                onResult = {}
                            )
                        }
                        items(
                            items = uiState.tasks, key = { it.id }
                        ) { task ->
                            TaskCardView(task = task, onItemClick = {
                                onEvent(TodoListEvent.OnItemClick(id = task.id))
                            })
                        }
                        item { Spacer(modifier = Modifier.height(24.dp)) }
                    }
                }
            }
        }
    }
}