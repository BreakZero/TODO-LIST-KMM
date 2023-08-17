package com.easy.todolist.android.feature.edit

sealed interface EditTaskEvent {
    object OnSave: EditTaskEvent
    data class OnTitleChanged(val title: String): EditTaskEvent
    data class OnDescriptionChanged(val description: String): EditTaskEvent
    data class OnDeadlineChanged(val deadline: String): EditTaskEvent
    data class OnAttachmentChanged(val attachment: String): EditTaskEvent
}