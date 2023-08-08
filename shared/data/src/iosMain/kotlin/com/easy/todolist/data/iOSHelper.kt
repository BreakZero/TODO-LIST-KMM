package com.easy.todolist.data

import com.easy.todolist.core.common.subscribe
import com.easy.todolist.data.di.dataModule
import com.easy.todolist.data.task.DefaultTaskRepository
import com.easy.todolist.data.user.DefaultUserRepository
import com.easy.todolist.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

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
}