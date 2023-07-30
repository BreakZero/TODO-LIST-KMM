package com.easy.todolist.android.feature.todo_list

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val TodoListRoute = "todo_list"

fun NavController.navigationToList(navOptions: NavOptions? = null) {
    this.navigate(TodoListRoute, navOptions)
}

fun NavGraphBuilder.bindTodoListGraph(
    toSettings: () -> Unit,
    viewDetail: (Long) -> Unit
) {
    composable(route = TodoListRoute) {
        val viewModel: TodoListViewModel = koinViewModel()
        LaunchedEffect(key1 = null) {
            viewModel.eventChannel.collect {
                when (it) {
                    is TodoListEvent.SettingClick -> toSettings()
                    is TodoListEvent.OnItemClick -> viewDetail(it.id)
                    else -> Unit
                }
            }
        }
        val uiState: TodoListUIState by viewModel.uiState.collectAsStateWithLifecycle()
        val isAddNewTaskOpen = viewModel.isAddNewTaskOpen
        val newTask = viewModel.newTask
        TodoListScreen(
            uiState = uiState,
            isAddNewTaskOpen = isAddNewTaskOpen,
            newTask = newTask,
            onEvent = viewModel::onEvent
        )
    }
}