package com.easy.todolist.data.user

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.easy.todolist.database.DatabaseDriverFactory
import com.easy.todolist.database.TodoListDatabase
import com.easy.todolist.database.createQueryWrapper
import com.easy.todolist.database.x.toEntity
import com.easy.todolist.database.x.toExternalModel
import com.easy.todolist.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultUserRepository constructor(
    private val driverFactory: DatabaseDriverFactory,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val queries = createQueryWrapper(driverFactory.createDriver())

    fun currentUserFlow(): Flow<User?> = queries.userQueries.findUser().asFlow().mapToOneOrNull(dispatcher).map {
        it?.toExternalModel()
    }

    suspend fun insertUser(
        user: User
    ) = withContext(dispatcher) {
        queries.transactionWithResult {
            val localUser = queries.userQueries.findUserByEmail(user.email).executeAsOneOrNull()
            if (localUser == null) {
                queries.userQueries.insertUser(user.toEntity())
            }
            localUser == null
        }
    }

    suspend fun queryUserByEmail(email: String) = withContext(dispatcher) {
        queries.userQueries.findUserByEmail(email).executeAsOneOrNull()?.toExternalModel()
    }
}