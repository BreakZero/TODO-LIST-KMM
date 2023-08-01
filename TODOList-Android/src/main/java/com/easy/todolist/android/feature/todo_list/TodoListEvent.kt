package com.easy.todolist.android.feature.todo_list

import com.easy.todolist.android.feature.add_edit.AddEditTaskEvent

sealed interface TodoListEvent {
    object AddNewTask: TodoListEvent
    object CloseAddNewSheet: TodoListEvent
    object SettingClick: TodoListEvent
    data class OnItemClick(val id: Long): TodoListEvent

    object OnSaveNewTask: TodoListEvent

    data class OnTitleChanged(val title: String): TodoListEvent
    data class OnDescriptionChanged(val description: String): TodoListEvent
    data class OnDeadlineChanged(val deadline: Long): TodoListEvent
    data class OnAttachmentChanged(val attachment: ByteArray): TodoListEvent

    object ChooseDeadline: TodoListEvent
    object CloseDeadlinePicker: TodoListEvent

    object ChooseImage: TodoListEvent
}