package com.easy.todolist.android.feature.sign_in

sealed interface SignInEvent {
    object OnSignupClick: SignInEvent
    data class OnEmailChanged(val email: String): SignInEvent
    data class OnPasswordChanged(val password: String): SignInEvent
    object OnSignIn: SignInEvent
}