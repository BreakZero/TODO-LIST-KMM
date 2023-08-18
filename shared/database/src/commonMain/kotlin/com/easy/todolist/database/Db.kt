package com.easy.todolist.database

import app.cash.sqldelight.db.SqlDriver
import com.easy.todolist.database.adapters.DateTimeAdapter

fun createQueryWrapper(driver: SqlDriver): TodoListDatabase {
    return TodoListDatabase(
        driver = driver,
        LocalTaskEntityAdapter = LocalTaskEntity.Adapter(
            createAtAdapter = DateTimeAdapter(),
            deadlineAdapter = DateTimeAdapter()
        )
    )
}
