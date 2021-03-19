package com.lanic.image.ui.search

sealed class LoadState {
    class Success(val isExist: Boolean) : LoadState()
    class Loading(val isLoading: Boolean) : LoadState()
    class Failed(val throwable: Throwable) : LoadState()

    interface Callback {
        fun setState(state: LoadState)
        fun onFailed(value: Failed)
    }
}
