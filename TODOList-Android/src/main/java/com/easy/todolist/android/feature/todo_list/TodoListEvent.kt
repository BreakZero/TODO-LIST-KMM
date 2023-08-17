package com.easy.todolist.android.feature.todo_list

sealed interface TodoListEvent {
    data object SettingClick: TodoListEvent
    data class OnItemClick(val id: Long): TodoListEvent

    data class OnTitleChanged(val title: String): TodoListEvent
    data class OnDescriptionChanged(val description: String): TodoListEvent
    data class OnDeadlineChanged(val deadline: Long): TodoListEvent
    data class OnAttachmentChanged(val attachment: ByteArray): TodoListEvent

    data object ShowDateTimePicker: TodoListEvent
    data object HideDateTimePicker: TodoListEvent
    data object ShowAddTaskSheet: TodoListEvent
    data object HideAddTaskSheet: TodoListEvent

    data object ShowImagePicker: TodoListEvent

    data object OnSaveNewTask: TodoListEvent

}