package com.easy.todolist.data.di

import com.easy.todolist.data.task.DefaultTaskRepository
import com.easy.todolist.data.user.DefaultUserRepository
import com.easy.todolist.database.di.factoryModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val dataModule = module {
    includes(factoryModule())

    single {
        DefaultTaskRepository(get(), Dispatchers.IO)
    }
    single {
        DefaultUserRepository(get(), Dispatchers.IO)
    }
}