package com.destro13.nord_task.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.formatDate(pattern: String): Long {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    return simpleDateFormat.parse(this)?.time ?: 0
}

fun Long.formatDate(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    val date = Date(this)
    return simpleDateFormat.format(date)
}