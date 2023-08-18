package com.easy.todolist.android.preview_data

import com.easy.todolist.android.enum.TaskCategory
import com.easy.todolist.model.Task

private val timestamp = 1692331754L
val defaultPreviewTask = Task(
    id = 0,
    title = "Title",
    description = "descriptionssssssss",
    deadline = timestamp,
    accentColor = TaskCategory.WORK.color,
    createAt = timestamp,
    attachment = null
)