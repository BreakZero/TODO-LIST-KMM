package com.easy.todolist.android.enum

enum class TaskCategory(
    val color: Long,
    val category: String
) {
    WORK(color = 0xFFF79E89, category = "WORK"),
    LIFE(color = 0xFFF76C6A, category = "LIFE"),
    GAMING(color = 0xFFF58B70, category = "GAMING")
}

