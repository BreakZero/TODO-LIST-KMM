package com.easy.todolist.data.user

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.easy.todolist.database.DatabaseDriverFactory
import com.easy.todolist.database.LocalUserEntity
import com.easy.todolist.database.TodoListDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultUserRepository constructor(
    private val driverFactory: DatabaseDriverFactory,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val queries = TodoListDatabase(driverFactory.createDriver())

    fun currentUserFlow(): Flow<Boolean> = queries.userQueries.findUser().asFlow().mapToOneOrNull(dispatcher).map {
        it != null
    }

    suspend fun insertUser(
        email: String,
        password: String
    ) = queries.userQueries.insertUser(LocalUserEntity(1, "Dougie Lu", email, password, 12))
}