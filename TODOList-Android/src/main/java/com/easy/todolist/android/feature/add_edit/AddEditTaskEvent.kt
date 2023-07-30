package com.easy.todolist.android.feature.add_edit

sealed interface AddEditTaskEvent {
    object OnSave: AddEditTaskEvent
    data class OnTitleChanged(val title: String): AddEditTaskEvent
    data class OnDescriptionChanged(val description: String): AddEditTaskEvent
    data class OnDeadlineChanged(val deadline: String): AddEditTaskEvent
    data class OnAttachmentChanged(val attachment: String): AddEditTaskEvent
}