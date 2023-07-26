package com.easy.todolist.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.data.user.DefaultUserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val userRepository: DefaultUserRepository
) : ViewModel() {
    val existUser = userRepository.currentUserFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), false)
}