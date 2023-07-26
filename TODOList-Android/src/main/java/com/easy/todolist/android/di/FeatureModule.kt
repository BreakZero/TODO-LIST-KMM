package com.easy.todolist.android.di

import com.easy.todolist.android.MainViewModel
import com.easy.todolist.android.feature.sign_in.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModules = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::SignInViewModel)
}
