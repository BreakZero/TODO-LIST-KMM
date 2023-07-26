package com.easy.todolist.android

import android.app.Application
import com.easy.todolist.android.di.commonModule
import com.easy.todolist.android.di.featureModules
import com.easy.todolist.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoListApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(commonModule)

            modules(dataModule)
            modules(featureModules)
        }
    }
}