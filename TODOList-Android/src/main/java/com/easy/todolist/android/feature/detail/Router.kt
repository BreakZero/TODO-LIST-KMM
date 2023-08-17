package com.easy.todolist.android.feature.detail

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.easy.todolist.android.common.ImagePicker
import com.easy.todolist.android.common.decoder.StringDecoder
import com.easy.todolist.android.feature.todo_list.TodoListEvent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

const val TaskDetailRoute = "task_detail"

internal const val taskIdKey = "arg_task_id"

internal class TaskArgs(val taskId: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
            this(stringDecoder.decodeString(checkNotNull(savedStateHandle[taskIdKey])))
}

fun NavController.navigateToTaskDetail(taskId: Long, navOptions: NavOptions? = null) {
    val encodeTaskId = Uri.encode(taskId.toString())
    this.navigate("$TaskDetailRoute/$encodeTaskId", navOptions)
}

fun NavGraphBuilder.bindTaskDetailGraph(
    popBack: () -> Unit
) {
    composable(
        route = "$TaskDetailRoute/{$taskIdKey}",
        arguments = listOf(
            navArgument(taskIdKey) { type = NavType.StringType }
        )
    ) {
        val activity = LocalContext.current as ComponentActivity
        val detailViewModel: TaskDetailViewModel = koinViewModel()
        val imagePicker = org.koin.androidx.compose.get<ImagePicker>() {
            parametersOf(activity)
        }
        LaunchedEffect(key1 = null, block = {
            detailViewModel.eventChannel.collect {
                when (it) {
                    is TaskDetailEvent.PopBack -> popBack()
                    is TaskDetailEvent.ShowEditSheet -> {}
                    is TaskDetailEvent.ShowImagePicker -> {
                        imagePicker.pickImage()
                    }
                    else -> Unit
                }
            }
        })
        val uiState by detailViewModel.uiState.collectAsStateWithLifecycle()
        val sheetUiState by detailViewModel.sheetUiState.collectAsStateWithLifecycle()
        TaskDetailScreen(
            uiState = uiState,
            sheetUiState = sheetUiState,
            editTask = detailViewModel.editTask,
            imagePicker = imagePicker,
            onEvent = detailViewModel::onEvent
        )
    }
}