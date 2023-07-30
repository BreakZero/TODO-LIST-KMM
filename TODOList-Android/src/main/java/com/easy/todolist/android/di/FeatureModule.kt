package com.easy.todolist.android.di

import com.easy.todolist.android.MainViewModel
import com.easy.todolist.android.feature.detail.TaskDetailViewModel
import com.easy.todolist.android.feature.settings.SettingsViewModel
import com.easy.todolist.android.feature.sign_in.SignInViewModel
import com.easy.todolist.android.feature.sign_up.SignUpViewModel
import com.easy.todolist.android.feature.todo_list.TodoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModules = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::TodoListViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::TaskDetailViewModel)
}
