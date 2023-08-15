package com.easy.todolist.data

import com.easy.todolist.core.common.subscribe
import com.easy.todolist.data.di.dataModule
import com.easy.todolist.data.task.DefaultTaskRepository
import com.easy.todolist.data.user.DefaultUserRepository
import com.easy.todolist.model.Task
import com.easy.todolist.model.User
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import platform.Foundation.NSData
import platform.Foundation.create
import platform.UIKit.UIImage

fun initKoin() {
    startKoin {
        modules(dataModule)
    }
}

class TodoHelper: KoinComponent {
    private val userRepository: DefaultUserRepository by inject()
    private val taskRepository: DefaultTaskRepository by inject()

    fun checkUser(
        onEach: (item: User?) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit
    ) = userRepository.currentUserFlow().subscribe(onEach, onComplete, onThrow)

    suspend fun insertOrUpdateUser(user: User) = userRepository.insertUser(user)

    fun loadTasks(
        onEach: (tasks: List<Task>) -> Unit,
        onComplete: () -> Unit,
        onError: (error: Throwable) -> Unit
    ) = taskRepository.loadTasks().subscribe(onEach, onComplete, onError)

    fun findTaskById(
        id: Long,
        onEach: (item:Task) -> Unit,
        onComplete: () -> Unit,
        onError: (error: Throwable) -> Unit
    ) = taskRepository.findTaskById(id).subscribe(onEach, onComplete, onError)

    suspend fun insertOrUpdateTask(task: Task) = taskRepository.insertTask(task)

    companion object {
        fun getUIImageFromBytes(bytes: ByteArray): UIImage {
            val data = bytes.usePinned {
                NSData.create(
                    bytes = it.addressOf(0),
                    length = bytes.size.toULong()
                )
            }
            return UIImage(data)
        }
    }
}