package com.easy.todolist.core.platform

import com.easy.todolist.core.commom.DateTimeDecoder
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter

actual class DateFormatHelper actual constructor(
    pattern: String
) {
    private val dateFormatter = NSDateFormatter().apply {
        dateFormat = pattern
    }
    actual fun formattedDateTime(localDateTime: LocalDateTime): String {
        val date = localDateTime.toInstant(DateTimeDecoder.defaultTimeZone).toNSDate()
        return dateFormatter.stringFromDate(date)
    }
}
