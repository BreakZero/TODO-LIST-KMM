package com.easy.todolist.database.adapters

import app.cash.sqldelight.ColumnAdapter
import com.easy.todolist.core.commom.DateTimeDecoder
import kotlinx.datetime.LocalDateTime

class DateTimeAdapter() : ColumnAdapter<LocalDateTime, Long> {
    override fun decode(databaseValue: Long): LocalDateTime {
        return DateTimeDecoder.decodeToDateTime(databaseValue)
    }

    override fun encode(value: LocalDateTime): Long {
        return DateTimeDecoder.encodeToLong(value)
    }

}