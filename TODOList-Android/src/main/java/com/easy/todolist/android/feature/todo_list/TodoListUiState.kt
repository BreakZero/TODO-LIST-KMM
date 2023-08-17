package com.easy.todolist.android.feature.todo_list

import com.easy.todolist.model.Task

data class TodoListUiState(
    val tasks: List<Task> = emptyList(),
    val isAddNewTaskOpen: Boolean = false,
    val isDatePickerOpen: Boolean = false,
)
