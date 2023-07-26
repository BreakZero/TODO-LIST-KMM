package com.easy.todolist.data.task

import com.easy.todolist.database.DatabaseDriverFactory
import com.easy.todolist.database.TodoListDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class DefaultTaskRepository constructor(
    private val driverFactory: DatabaseDriverFactory,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val queries = TodoListDatabase(driverFactory.createDriver()).taskQueries
}