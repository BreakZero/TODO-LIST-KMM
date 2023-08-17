package com.easy.todolist.android.feature.sign_up

data class SignUpUiState(
    val email: String = "",
    val fullName: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val error: String? = null,
) {
    fun isActive(): Boolean {
        return (email.isNotBlank() && fullName.isNotBlank() &&
                password.isNotBlank() && confirmPassword.isNotBlank() && confirmPassword == password)
    }
}
