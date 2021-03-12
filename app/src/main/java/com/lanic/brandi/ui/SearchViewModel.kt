package com.lanic.brandi.ui

import androidx.lifecycle.ViewModel
import com.lanic.brandi.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getSearchImage(query: String, page: String, size: String) {
        searchRepository.getSearchImage(query, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                Timber.e(it)
            }).addTo(compositeDisposable)
    }
}