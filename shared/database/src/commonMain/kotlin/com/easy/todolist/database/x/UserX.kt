package com.easy.todolist.database.x

import com.easy.todolist.database.LocalUserEntity
import com.easy.todolist.model.User

fun LocalUserEntity.toExternalModel(): User {
    return User(uid, fullName, email, createAt)
}

fun User.toEntity(): LocalUserEntity {
    return LocalUserEntity(uid, fullName, email, createAt)
}