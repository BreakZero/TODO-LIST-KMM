package com.easy.todolist.android.feature.detail

sealed interface TaskDetailEvent {
    data object ShowDeleteActions: TaskDetailEvent
    data object HideDeleteActions: TaskDetailEvent
    data object PopBack : TaskDetailEvent
    data object ShowEditSheet : TaskDetailEvent
    data object HideEditSheet: TaskDetailEvent
    data object ShowDateTimePicker: TaskDetailEvent
    data object HideDateTimePicker: TaskDetailEvent
    data object ShowImagePicker: TaskDetailEvent

    data object OnConfirmed: TaskDetailEvent


    data object OnDelete : TaskDetailEvent

    data class OnTitleChanged(val title: String): TaskDetailEvent
    data class OnDescriptionChanged(val description: String): TaskDetailEvent
    data class OnDeadlineChanged(val deadline: Long): TaskDetailEvent
    data class OnAttachmentChanged(val attachment: ByteArray): TaskDetailEvent
}