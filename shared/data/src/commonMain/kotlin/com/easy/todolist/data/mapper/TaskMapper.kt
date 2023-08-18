package com.easy.todolist.data.mapper

import com.easy.todolist.core.platform.ImageStorage
import com.easy.todolist.core.commom.DateTimeDecoder
import com.easy.todolist.database.LocalTaskEntity
import com.easy.todolist.model.Task

suspend fun LocalTaskEntity.toExternalModel(imageStorage: ImageStorage): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        accentColor = accentColor,
        deadline = DateTimeDecoder.encodeToLong(deadline),
        attachment = attachmentPath?.let { imageStorage.getImage(it) },
        createAt = DateTimeDecoder.encodeToLong(createAt)
    )
}
