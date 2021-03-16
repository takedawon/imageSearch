package com.lanic.image.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun showProgress() {
        _loading.postValue(true)
    }

    fun hideProgress() {
        _loading.postValue(false)
    }
}