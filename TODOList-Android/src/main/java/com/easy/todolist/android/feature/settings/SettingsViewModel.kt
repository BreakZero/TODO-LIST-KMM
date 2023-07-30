package com.easy.todolist.android.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.data.user.DefaultUserRepository
import com.easy.todolist.model.User
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(userRepository: DefaultUserRepository): ViewModel() {

    private val _eventChannel = Channel<SettingsEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    val profileUiState = userRepository.currentUserFlow().map {
        ProfileUiState(user = it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), ProfileUiState())

    fun onEvent(event: SettingsEvent) {
        viewModelScope.launch {
            _eventChannel.send(event)
        }
    }
}

data class ProfileUiState(
    val user: User? = null
)