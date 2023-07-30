package com.easy.todolist.android.feature.detail

import com.easy.todolist.model.Task

sealed interface TaskDetailUIState {
    object Loading: TaskDetailUIState
    data class Success(val task: Task): TaskDetailUIState
}


