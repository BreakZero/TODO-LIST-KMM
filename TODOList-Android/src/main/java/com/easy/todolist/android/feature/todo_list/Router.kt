package com.easy.todolist.android.feature.todo_list

import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easy.todolist.android.common.ImagePicker
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
        val activity = LocalContext.current as ComponentActivity
        val viewModel: TodoListViewModel = koinViewModel()
        val imagePicker = remember(activity) {
            ImagePicker(activity)
        }
        LaunchedEffect(key1 = viewModel) {
            viewModel.eventChannel.collect {
                when (it) {
                    is TodoListEvent.SettingClick -> toSettings()
                    is TodoListEvent.OnItemClick -> viewDetail(it.id)
                    is TodoListEvent.ChooseImage -> {
                        imagePicker.pickImage()
                    }
                    else -> Unit
                }
            }
        }

        val uiState: TodoListUIState by viewModel.uiState.collectAsStateWithLifecycle()
        val newTask = viewModel.newTask
        TodoListScreen(
            uiState = uiState,
            newTask = newTask,
            onEvent = viewModel::onEvent,
            imagePicker = imagePicker
        )
    }
}