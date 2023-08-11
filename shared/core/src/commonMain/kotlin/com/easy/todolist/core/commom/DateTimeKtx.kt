package com.easy.todolist.core.commom

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long?.getFormattedDateTime(
    default: String = "--"
): String {
    return this?.let {
        val instant = Instant.fromEpochMilliseconds(it)
        instant.toLocalDateTime(TimeZone.UTC).toString()
    } ?: default
}
