package com.easy.todolist.android.feature.todo_list

import com.easy.todolist.model.Task

sealed interface TodoListUIState {
    object Loading : TodoListUIState
    data class Success(val tasks: List<Task>) : TodoListUIState

    data class Error(val error: String) : TodoListUIState
}
