package com.lanic.brandi.ui.search

import androidx.paging.PageKeyedDataSource
import com.lanic.brandi.data.repository.SearchRepository
import com.lanic.brandi.data.response.Document
import com.lanic.brandi.data.response.SearchResponse
import com.lanic.brandi.ui.search.SearchFragment.Companion.LOAD_DATA_SIZE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

//https://dapi.kakao.com/v2/search/image?query=%ED%95%98%ED%95%98&page=1&size=30
class SearchDataSource constructor(
    val text: () -> String,
    val searchRepository: SearchRepository,
    val compositeDisposable: CompositeDisposable,
) : PageKeyedDataSource<Int, Document>() {

    private var searchQuery: String = ""

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Document>
    ) {
        val page = 1
        val text = text()

        if (text.isBlank().not()) {
            searchQuery = text

            getSearchImage(searchQuery, page)
                .subscribeBy(onSuccess = {
                    callback.onResult(it.documents, null, page + 1)
                }, onError = {

                }).addTo(compositeDisposable)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Document>
    ) {
        getSearchImage(searchQuery, params.key)
            .subscribeBy(onSuccess = {
                callback.onResult(it.documents, params.key-1)
            }, onError = {

            }).addTo(compositeDisposable)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Document>
    ) {
        getSearchImage(searchQuery, params.key)
            .subscribeBy(onSuccess = {
                callback.onResult(it.documents, params.key+1)
            }, onError = {

            }).addTo(compositeDisposable)
    }

    private fun getSearchImage(query: String, page: Int): Single<SearchResponse> {
        return searchRepository.getSearchImage(query, page.toString(), LOAD_DATA_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}