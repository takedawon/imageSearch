package com.lanic.image.util

import java.text.SimpleDateFormat
import java.util.Locale

fun getDateFormat(date: String): String {
    return try {
        val dateString = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(date)
        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(dateString)
    } catch (e: Exception) {
        date
    }
}
