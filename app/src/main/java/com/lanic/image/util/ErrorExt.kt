package com.lanic.image.util

import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun errorMapper(error: MutableLiveData<Event<String>>, throwable: Throwable) {
    val serverErrorMsg = Event("서버와의 연결이 불안정합니다. 나중에 다시 시도해주세요.")
    when (throwable) {
        is HttpException -> {
            error.value = when (throwable.code() / 100) {
                4 -> {
                    Event("요청에 실패하였습니다. 다시 시도해주세요.")
                }
                else -> {
                    serverErrorMsg
                }
            }
        }
        is SocketTimeoutException, is UnknownHostException -> {
            error.value = serverErrorMsg
        }
    }
}