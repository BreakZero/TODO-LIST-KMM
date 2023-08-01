package com.easy.todolist.data.task

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.easy.todolist.core.ImageStorage
import com.easy.todolist.data.mapper.toExternalModel
import com.easy.todolist.database.DatabaseDriverFactory
import com.easy.todolist.database.TodoListDatabase
import com.easy.todolist.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class DefaultTaskRepository constructor(
    private val driverFactory: DatabaseDriverFactory,
    private val imageStorage: ImageStorage,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val queries = TodoListDatabase(driverFactory.createDriver()).taskQueries

    fun loadTasks() = queries.findAllTasks().asFlow().mapToList(dispatcher).map { taskEntities ->
        supervisorScope {
            taskEntities
                .map {
                    async { it.toExternalModel(imageStorage) }
                }.map {
                    it.await()
                }
        }
    }

    suspend fun insertTask(task: Task) = withContext(dispatcher) {
        val imagePath = task.attachment?.let {
            imageStorage.saveImage(it)
        }
        queries.insertTask(
            title = task.title,
            description = task.description,
            accentColor = task.accentColor,
            attachmentPath = imagePath,
            deadline = task.deadline,
            createAt = task.createAt
        )
    }

    fun findTaskById(id: Long) =
        queries.findTaskById(id).asFlow().mapToOne(dispatcher).map {
            it.toExternalModel(imageStorage)
        }
}

object TaskValidator {
    fun validateTask(task: Task): ValidationResult {
        var result = ValidationResult()
        if (task.title.isBlank()) {
            result = result.copy(titleError = "The title can't be empty.")
        }
        if (task.description.isBlank()) {
            result = result.copy(titleError = "The description can't be empty.")
        }
        return result
    }

    data class ValidationResult(
        val titleError: String? = null,
        val descriptionError: String? = null
    )
}