package com.lanic.image.data.datasource

import androidx.paging.PageKeyedDataSource
import com.lanic.image.data.repository.SearchRepository
import com.lanic.image.data.response.SearchImage
import com.lanic.image.data.response.SearchResponse
import com.lanic.image.ui.search.LoadState
import com.lanic.image.ui.search.SearchFragment.Companion.LOAD_DATA_SIZE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchDataSource constructor(
    val searchText: () -> String,
    private val searchRepository: SearchRepository,
    private val compositeDisposable: CompositeDisposable,
    private val loadState: LoadState.Callback
) : PageKeyedDataSource<Int, SearchImage>() {

    private var searchQuery: String = ""

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SearchImage>
    ) {
        val page = 1
        val text = searchText()

        if (text.isBlank().not()) {
            searchQuery = text

            getSearchImage(searchQuery, page)
                .subscribeBy(onSuccess = { response ->
                    if (response.meta.isEnd) {
                        loadState.onSuccess(LoadState.Success(false))
                    } else {
                        loadState.onSuccess(LoadState.Success(true))
                        callback.onResult(response.searchImages, null, page + 1)
                    }
                }, onError = { throwble ->
                    loadState.onFailed(LoadState.Failed(throwble))
                }).addTo(compositeDisposable)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, SearchImage>
    ) {
        getSearchImage(searchQuery, params.key)
            .doOnSubscribe { loadState.onLoading(LoadState.Loading(true)) }
            .doAfterTerminate { loadState.onLoading(LoadState.Loading(false)) }
            .subscribeBy(onSuccess = { response ->
                callback.onResult(response.searchImages, params.key - 1)
            }, onError = { throwble ->
                loadState.onFailed(LoadState.Failed(throwble))
            }).addTo(compositeDisposable)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, SearchImage>
    ) {
        getSearchImage(searchQuery, params.key)
            .doOnSubscribe { loadState.onLoading(LoadState.Loading(true)) }
            .doAfterTerminate { loadState.onLoading(LoadState.Loading(false)) }
            .subscribeBy(onSuccess = { response ->
                callback.onResult(response.searchImages, params.key + 1)
            }, onError = { throwble ->
                loadState.onFailed(LoadState.Failed(throwble))
            }).addTo(compositeDisposable)
    }

    private fun getSearchImage(query: String, page: Int): Single<SearchResponse> {
        return searchRepository.getSearchImage(query, page.toString(), LOAD_DATA_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
