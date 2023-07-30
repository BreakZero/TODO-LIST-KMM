package com.easy.todolist.model

data class User(
    val uid: String,
    val fullName: String,
    val email: String,
    val createAt: Long
)
