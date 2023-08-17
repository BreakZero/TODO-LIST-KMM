package com.easy.todolist.android.feature.sign_in

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null
) {
    fun isActive(): Boolean {
        return !(email.isBlank() || password.isBlank())
    }
}
