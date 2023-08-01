package com.easy.todolist.core.di

import com.easy.todolist.core.ImageStorage
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun coreModule() = module {
    singleOf(::ImageStorage)
}