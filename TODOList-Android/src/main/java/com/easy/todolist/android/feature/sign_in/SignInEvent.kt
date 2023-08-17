package com.easy.todolist.android.feature.sign_in

sealed interface SignInEvent {
    data object SignUpClicked: SignInEvent
    data class OnEmailChanged(val email: String): SignInEvent
    data class OnPasswordChanged(val password: String): SignInEvent
    data object OnSignIn: SignInEvent
}