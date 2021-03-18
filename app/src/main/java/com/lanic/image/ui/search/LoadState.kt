package com.lanic.image.ui.search

sealed class LoadState<T> {
    class Success(val isExist: Boolean) : LoadState<Boolean>()
    class Loading(val isLoading: Boolean) : LoadState<Boolean>()
    class Failed(val throwable: Throwable) : LoadState<Nothing>()

    interface Callback {
        fun onSuccess(value: Success)
        fun onLoading(value: Loading)
        fun onFailed(value: Failed)
    }
}