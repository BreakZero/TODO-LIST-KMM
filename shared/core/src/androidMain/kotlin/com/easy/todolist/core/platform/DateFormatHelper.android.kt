package com.easy.todolist.core.platform

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

actual class DateFormatHelper actual constructor(pattern: String) {

    private val defaultFormatter = DateTimeFormatter.ofPattern(pattern)

    actual fun formattedDateTime(localDateTime: LocalDateTime): String {
        val javaDateTime = localDateTime.toJavaLocalDateTime()
        return javaDateTime.format(defaultFormatter)
    }
}
