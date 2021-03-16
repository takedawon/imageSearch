package com.lanic.image.util

import java.text.SimpleDateFormat
import java.util.*

fun getDateFormat(date: String): String {
    return try {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(date)
        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(date)
    } catch (e: Exception) {
        date
    }
}