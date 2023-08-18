package com.easy.todolist.core.commom

import com.easy.todolist.core.platform.DateFormatHelper
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeDecoder {
    val defaultTimeZone = TimeZone.of("UTC+8")

    fun decodeToDateTime(timestamp: Long): LocalDateTime {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        return instant.toLocalDateTime(defaultTimeZone)
    }

    fun encodeToLong(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(defaultTimeZone).toEpochMilliseconds()
    }
}
