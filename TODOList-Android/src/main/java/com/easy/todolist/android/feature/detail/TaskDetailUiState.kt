package com.easy.todolist.android.feature.detail

import com.easy.todolist.model.Task

sealed interface TaskDetailUiState {
    data object Loading: TaskDetailUiState
    data class Success(
        val task: Task
    ): TaskDetailUiState
}

data class TaskDetailSheetUiState(
    val isEditSheetOpen: Boolean = false,
    val isDateTimePickerOpen: Boolean = false,
    val isDeleteActionOpen: Boolean = false
)


