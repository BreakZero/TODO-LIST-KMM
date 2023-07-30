package com.easy.todolist.android.feature.todo_list

import com.easy.todolist.model.Task

data class TodoListUIState(
    val tasks: List<Task> = emptyList(),
    val isAddNewTaskOpen: Boolean = false,
    val isDatePickerOpen: Boolean = false,
    val isImagePickerOpen: Boolean = false
)
