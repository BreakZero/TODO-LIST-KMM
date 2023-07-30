package com.easy.todolist.data.task

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.easy.todolist.database.DatabaseDriverFactory
import com.easy.todolist.database.LocalTaskEntity
import com.easy.todolist.database.TodoListDatabase
import com.easy.todolist.database.x.toEntity
import com.easy.todolist.database.x.toExternalModel
import com.easy.todolist.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultTaskRepository constructor(
    private val driverFactory: DatabaseDriverFactory,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val queries = TodoListDatabase(driverFactory.createDriver()).taskQueries

    fun loadTasks() = queries.findAllTasks().asFlow().mapToList(dispatcher).map {
        it.map(LocalTaskEntity::toExternalModel)
    }

    suspend fun insertTask(task: Task) = withContext(dispatcher) {
        queries.insertTask(task.toEntity())
    }

    fun findTaskById(id: Long) = queries.findTaskById(id).asFlow().mapToOne(dispatcher).map(LocalTaskEntity::toExternalModel)
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