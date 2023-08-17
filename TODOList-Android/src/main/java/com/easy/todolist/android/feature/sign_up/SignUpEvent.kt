package com.easy.todolist.android.feature.sign_up

sealed interface SignUpEvent {
    data object SignInClicked : SignUpEvent
    data class OnEmailChanged(val email: String) : SignUpEvent
    data class OnFullNameChanged(val fullName: String): SignUpEvent
    data class OnPasswordChanged(val password: String): SignUpEvent
    data class OnConfirmPasswordChanged(val password: String): SignUpEvent
    data object OnSignUp: SignUpEvent
}
