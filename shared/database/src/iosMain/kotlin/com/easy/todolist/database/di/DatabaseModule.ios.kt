package com.easy.todolist.database.di

import com.easy.todolist.database.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun factoryModule() = module {
    singleOf(::DatabaseDriverFactory)
}