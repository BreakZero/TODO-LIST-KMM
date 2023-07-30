package com.easy.todolist.database.x

import com.easy.todolist.database.LocalTaskEntity
import com.easy.todolist.model.Task

fun LocalTaskEntity.toExternalModel(): Task {
    return Task(
        id, title, description, accentColor, deadline, attachment, createAt
    )
}

fun Task.toEntity(): LocalTaskEntity {
    return LocalTaskEntity(
        id, title, description, accentColor, deadline, attachment, createAt
    )
}