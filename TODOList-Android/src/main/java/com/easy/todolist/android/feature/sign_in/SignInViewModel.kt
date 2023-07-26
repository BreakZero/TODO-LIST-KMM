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
    private val _state = MutableStateFlow(SignInUIState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<SignInEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()


    fun onEvent(signInEvent: SignInEvent) {
        when(signInEvent) {
            is SignInEvent.OnSignIn -> {
                viewModelScope.launch {
                    userRepository.insertUser(_state.value.email, _state.value.password)
                }
            }
            is SignInEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(email = signInEvent.password)
                }
            }
            is SignInEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(email = signInEvent.email)
                }
            }
            is SignInEvent.OnSignupClick -> {
                viewModelScope.launch {
                    _eventChannel.send(signInEvent)
                }
            }
        }
    }
}