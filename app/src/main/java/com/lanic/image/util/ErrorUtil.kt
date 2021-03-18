package com.lanic.image.util

import com.lanic.image.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun toErrorResource(throwable: Throwable): Int {
    return when (throwable) {
        is HttpException -> {
            when (throwable.code() / 100) {
                4 -> {
                    R.string.request_fail
                }
                else -> {
                    R.string.unstable_connection
                }
            }
        }
        is SocketTimeoutException, is UnknownHostException -> {
            R.string.unstable_connection
        }
        else -> R.string.try_again
    }
}