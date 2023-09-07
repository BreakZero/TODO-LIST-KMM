package com.easy.todolist.data

import com.easy.todolist.data.di.dataModule
import org.koin.core.context.startKoin
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "initKoin")
fun initKoin() {
    startKoin {
        modules(dataModule)
    }
}
