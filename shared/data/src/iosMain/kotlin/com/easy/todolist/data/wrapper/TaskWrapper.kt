package com.easy.todolist.data.wrapper

import com.easy.todolist.core.common.subscribe
import com.easy.todolist.core.common.wrap
import com.easy.todolist.data.task.DefaultTaskRepository
import com.easy.todolist.model.Task
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "TaskRepository")
class TaskWrapper: KoinComponent {
    private val taskRepository: DefaultTaskRepository by inject()

    @ObjCName("loadTasks")
    fun taskStream(
        onEach: (tasks: List<Task>) -> Unit,
        onComplete: () -> Unit,
        onError: (error: Throwable) -> Unit
    ) = taskRepository.loadTasks().subscribe(onEach, onComplete, onError)

    fun findTaskById(
        id: Long,
        onEach: (item: Task) -> Unit,
        onComplete: () -> Unit,
        onError: (error: Throwable) -> Unit
    ) = taskRepository.findTaskById(id).subscribe(onEach, onComplete, onError)

    suspend fun insertTask(task: Task) = suspend { taskRepository.insertTask(task) }.wrap()

    suspend fun updateTask(task: Task) = suspend {
        println("===== ${task.description}")
        taskRepository.upsertTask(task) }.wrap()
    suspend fun deleteById(id: Long) = suspend { taskRepository.deleteById(id) }.wrap()
}