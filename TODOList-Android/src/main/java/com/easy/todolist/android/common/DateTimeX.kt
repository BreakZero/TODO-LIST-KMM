package com.easy.todolist.android.common

import com.easy.todolist.core.commom.DateTimeDecoder
import com.easy.todolist.core.platform.DateFormatHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object DateTimeX: KoinComponent {
    private val dateFormatHelper: DateFormatHelper by inject()
    fun formattedDate(timestamp: Long?, default: String = "--"): String {
        val dateTime = timestamp?.let { DateTimeDecoder.decodeToDateTime(it) }
        return dateTime?.let {
            dateFormatHelper.formattedDateTime(dateTime)
        } ?: default
    }
}
