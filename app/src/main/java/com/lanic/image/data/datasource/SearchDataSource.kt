package com.lanic.image.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.lanic.image.data.repository.SearchRepository
import com.lanic.image.data.response.SearchImage
import com.lanic.image.data.response.SearchResponse
import com.lanic.image.ui.search.SearchFragment.Companion.LOAD_DATA_SIZE
import com.lanic.image.ui.search.SearchImageAdapter
import com.lanic.image.util.Event
import com.lanic.image.util.errorMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchDataSource constructor(
    val text: () -> String,
    private val searchRepository: SearchRepository,
    private val compositeDisposable: CompositeDisposable,
    private val isSearchResult: MutableLiveData<Boolean>,
    private val error: MutableLiveData<Event<String>>,
) : PageKeyedDataSource<Int, SearchImage>() {

    private var searchQuery: String = ""

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SearchImage>
    ) {
        val page = 1
        val text = text()

        if (text.isBlank().not()) {
            searchQuery = text

            getSearchImage(searchQuery, page)
                .subscribeBy(onSuccess = { response ->
                    if (response.meta.isEnd) {
                        isSearchResult.value = false
                    } else {
                        isSearchResult.value = true
                        callback.onResult(
                            addProgressViewHolder(response.searchImages.toMutableList()),
                            null,
                            page + 1
                        )
                    }
                }, onError = { throwble ->
                    errorMapper(error, throwble)
                }).addTo(compositeDisposable)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, SearchImage>
    ) {
        getSearchImage(searchQuery, params.key)
            .subscribeBy(onSuccess = { response ->
                callback.onResult(response.searchImages, params.key - 1)
            }, onError = { throwble ->
                errorMapper(error, throwble)
            }).addTo(compositeDisposable)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, SearchImage>
    ) {
        getSearchImage(searchQuery, params.key)
            .subscribeBy(onSuccess = { response ->
                callback.onResult(
                    addProgressViewHolder(response.searchImages.toMutableList()),
                    params.key + 1
                )
            }, onError = { throwble ->
                errorMapper(error, throwble)
            }).addTo(compositeDisposable)
    }

    private fun getSearchImage(query: String, page: Int): Single<SearchResponse> {
        return searchRepository.getSearchImage(query, page.toString(), LOAD_DATA_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun addProgressViewHolder(searchImages: MutableList<SearchImage>): List<SearchImage> {
        return searchImages.apply {
            add(SearchImage(type = SearchImageAdapter.PROGRESS))
        }
    }
}