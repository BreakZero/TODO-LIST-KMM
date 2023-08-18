package com.easy.todolist.core.di

import com.easy.todolist.core.platform.ImageStorage
import com.easy.todolist.core.platform.DateFormatHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun coreModule() = module {
    singleOf(::ImageStorage)
    single {
        DateFormatHelper("y, MMM d, HH:mm")
    }
}