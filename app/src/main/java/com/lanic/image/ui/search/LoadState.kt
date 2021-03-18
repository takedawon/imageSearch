package com.lanic.image.ui.search

sealed class LoadState<out T> {
    class Success<out T>(item: T) : LoadState<T>()
    class Loading(val isLoading: Boolean) : LoadState<Boolean>()
    class Failed(throwable: Throwable) : LoadState<Nothing>()

    interface Callback<T> {
        fun onSuccess(value: Success<T>)
        fun onLoading(value: Loading)
        fun onFailed(value: Failed)
    }
}