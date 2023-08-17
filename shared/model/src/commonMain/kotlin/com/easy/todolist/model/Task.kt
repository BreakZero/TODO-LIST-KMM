package com.easy.todolist.model

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val accentColor: Long,
    val deadline: Long,
    val attachment: ByteArray?,
    val createAt: Long,
) {
    fun inProgress(): Boolean {
        return false
    }

    override fun toString(): String {
        return """
            id: $id,
            title: $title,
            description: $description,
            accentColor: $accentColor,
            deadline: $deadline,
            attachment: ${attachment?.size},
            createAt: $createAt
        """.trimIndent()
    }
}
