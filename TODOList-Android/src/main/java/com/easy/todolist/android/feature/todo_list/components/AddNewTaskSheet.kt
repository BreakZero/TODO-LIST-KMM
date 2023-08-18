package com.easy.todolist.android.feature.todo_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.easy.todolist.android.components.TaskFormEditor
import com.easy.todolist.android.feature.todo_list.TodoListEvent
import com.easy.todolist.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTaskSheet(
    modifier: Modifier = Modifier,
    task: Task?,
    onEvent: (TodoListEvent) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = {
            onEvent(TodoListEvent.HideAddTaskSheet)
        }
    ) {
        TaskFormEditor(
            modifier = Modifier.fillMaxWidth(),
            task = task,
            confirmButtonText = "ADD TODO",
            onTitleChanged = {
                onEvent(TodoListEvent.OnTitleChanged(it))
            },
            onDescriptionChanged = {
                onEvent(TodoListEvent.OnDescriptionChanged(it))
            },

            openDatePicker = {
                onEvent(TodoListEvent.ShowDateTimePicker)
            },
            openImagePicker = {
                onEvent(TodoListEvent.ShowImagePicker)
            },
            onConfirmed = {
                onEvent(TodoListEvent.OnSaveNewTask)
            }
        )
    }
}