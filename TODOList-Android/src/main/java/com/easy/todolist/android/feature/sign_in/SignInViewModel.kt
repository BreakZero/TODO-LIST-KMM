package com.easy.todolist.android.feature.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.data.user.DefaultUserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val userRepository: DefaultUserRepository
): ViewModel() {
    private val _state = MutableStateFlow(SignInUiState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<SignInEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()


    fun onEvent(signInEvent: SignInEvent) {
        when(signInEvent) {
            is SignInEvent.OnSignIn -> {
                viewModelScope.launch {
                    // TODO check user information
                    val isExisted = userRepository.queryUserByEmail(_state.value.email) != null
                    if (isExisted) {
                        _eventChannel.send(signInEvent)
                    } else {
                        // TODO user not found
                    }
                }
            }
            is SignInEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(password = signInEvent.password)
                }
            }
            is SignInEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(email = signInEvent.email)
                }
            }
            is SignInEvent.SignUpClicked -> {
                viewModelScope.launch {
                    _eventChannel.send(signInEvent)
                }
            }
        }
    }
}