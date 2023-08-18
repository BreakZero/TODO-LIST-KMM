package com.easy.todolist.core.platform

import kotlinx.datetime.LocalDateTime

expect class DateFormatHelper(pattern: String) {
    fun formattedDateTime(localDateTime: LocalDateTime): String
}
