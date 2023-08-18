package com.easy.todolist.android.feature.detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easy.todolist.android.components.TaskFormEditor
import com.easy.todolist.android.feature.detail.TaskDetailEvent
import com.easy.todolist.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskSheet(
    modifier: Modifier = Modifier,
    task: Task,
    onEvent: (TaskDetailEvent) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = {
            onEvent(TaskDetailEvent.HideEditSheet)
        }
    ) {
        TaskFormEditor(
            modifier = Modifier.fillMaxWidth(),
            task = task,
            confirmButtonText = "Edit Task",
            onTitleChanged = {
                onEvent(TaskDetailEvent.OnTitleChanged(it))
            },
            onDescriptionChanged = {
                onEvent(TaskDetailEvent.OnDescriptionChanged(it))
            },
            openDatePicker = {
                onEvent(TaskDetailEvent.ShowDateTimePicker)
            },
            openImagePicker = {
                onEvent(TaskDetailEvent.ShowImagePicker)
            },
            onConfirmed = {
                onEvent(TaskDetailEvent.OnConfirmed)
            }
        )
    }
}