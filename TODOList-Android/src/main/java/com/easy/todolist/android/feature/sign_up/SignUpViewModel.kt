package com.easy.todolist.android.feature.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.core.commom.systemCurrentMilliseconds
import com.easy.todolist.data.user.DefaultUserRepository
import com.easy.todolist.model.User
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class SignUpViewModel(
    private val userRepository: DefaultUserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpUiState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<SignUpEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SignInClicked -> {
                viewModelScope.launch {
                    _eventChannel.send(SignUpEvent.SignInClicked)
                }
            }

            is SignUpEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(email = event.email)
                }
            }

            is SignUpEvent.OnFullNameChanged -> {
                _state.update {
                    it.copy(fullName = event.fullName)
                }
            }

            is SignUpEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(password = event.password)
                }
            }

            is SignUpEvent.OnConfirmPasswordChanged -> {
                _state.update {
                    it.copy(confirmPassword = event.password)
                }
            }

            is SignUpEvent.OnSignUp -> {
                viewModelScope.launch {
                    val insertSuccessful = userRepository.insertUser(
                        User(
                            uid = UUID.randomUUID().toString(),
                            fullName = _state.value.fullName,
                            email = _state.value.email,
                            createAt = systemCurrentMilliseconds()
                        )
                    )
                    if (insertSuccessful) _eventChannel.send(SignUpEvent.OnSignUp)
                    else {
                        // TODO update error state
                    }
                }
            }
        }
    }

}