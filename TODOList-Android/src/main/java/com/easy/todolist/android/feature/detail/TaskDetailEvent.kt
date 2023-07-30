package com.easy.todolist.android.feature.detail

sealed interface TaskDetailEvent {
    object OnDelete : TaskDetailEvent
    object OnEdit : TaskDetailEvent
    object PopBack : TaskDetailEvent
}