package com.easy.todolist.android.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Long.toDate(): String {
    return SimpleDateFormat("MM/dd/yyyy").format(Date(this))
}