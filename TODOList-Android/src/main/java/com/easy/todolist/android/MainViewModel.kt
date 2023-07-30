package com.easy.todolist.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.data.user.DefaultUserRepository
import com.easy.todolist.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val userRepository: DefaultUserRepository
) : ViewModel() {
    val mainUIState = userRepository.currentUserFlow().map<User?, MainUIState>(MainUIState::Success)
        .onStart {
            emit(MainUIState.Loading)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), MainUIState.Loading)
}

sealed interface MainUIState {
    object Loading: MainUIState
    data class Success(val user: User?): MainUIState
}