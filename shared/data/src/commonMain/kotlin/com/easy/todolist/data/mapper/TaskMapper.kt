package com.easy.todolist.data.mapper

import com.easy.todolist.core.ImageStorage
import com.easy.todolist.database.LocalTaskEntity
import com.easy.todolist.model.Task

suspend fun LocalTaskEntity.toExternalModel(imageStorage: ImageStorage): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        accentColor = accentColor,
        deadline = deadline,
        attachment = attachmentPath?.let { imageStorage.getImage(it) },
        createAt = createAt
    )
}
