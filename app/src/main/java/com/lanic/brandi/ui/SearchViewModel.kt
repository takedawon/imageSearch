package com.lanic.brandi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lanic.brandi.data.repository.SearchRepository
import com.lanic.brandi.data.response.Document
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

    private val _searchImage = MutableLiveData<List<Document>>()
    val searchImage: LiveData<List<Document>> = _searchImage

    fun getSearchImage(query: String, page: String, size: String) {
        searchRepository.getSearchImage(query, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _searchImage.value = response.documents
            }, {
                Timber.e(it)
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}