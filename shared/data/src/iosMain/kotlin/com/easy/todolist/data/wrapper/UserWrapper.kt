package com.easy.todolist.data.wrapper

import com.easy.todolist.core.common.subscribe
import com.easy.todolist.core.common.wrap
import com.easy.todolist.data.user.DefaultUserRepository
import com.easy.todolist.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "UserRepository")
class UserWrapper: KoinComponent {
    private val userRepository: DefaultUserRepository by inject()

    fun checkUser(
        onEach: (item: User?) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit
    ) = userRepository.currentUserFlow().subscribe(onEach, onComplete, onThrow)

    suspend fun insertOrUpdateUser(user: User) = suspend { userRepository.insertUser(user) }.wrap()
}