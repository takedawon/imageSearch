package com.lanic.image.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lanic.image.base.BaseViewModel
import com.lanic.image.data.datasource.SearchDataSource
import com.lanic.image.data.repository.SearchRepository
import com.lanic.image.data.response.SearchImage
import com.lanic.image.util.Event
import com.lanic.image.util.toErrorResource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    BaseViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val _isSearchResult: MutableLiveData<Boolean> = MutableLiveData()
    var isSearchResult: LiveData<Boolean> = _isSearchResult

    private val _error: MutableLiveData<Event<Int>> = MutableLiveData()
    var error: LiveData<Event<Int>> = _error

    private val _clear: MutableLiveData<Event<Unit>> = MutableLiveData()
    var clear: LiveData<Event<Unit>> = _clear

    private val _bottomProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    val bottomProgress: LiveData<Boolean> = _bottomProgress

    val searchImage: LiveData<PagedList<SearchImage>> = createSearchLiveData()

    var searchText = MutableLiveData<String>()

    var publishSubject: PublishSubject<String>

    private var searchDataSource: SearchDataSource? = null

    val loadState = object : LoadState.Callback {
        override fun setState(state: LoadState) {
            when (state) {
                is LoadState.Success -> {
                    _isSearchResult.value = state.isExist
                }
                is LoadState.Loading -> {
                    _bottomProgress.postValue(state.isLoading)
                }
                is LoadState.Failed -> {
                    _error.value = Event(toErrorResource(state.throwable))
                }
            }
        }

        override fun onFailed(value: LoadState.Failed) {
            _error.value = Event(toErrorResource(value.throwable))
        }
    }

    init {
        publishSubject = PublishSubject.create()
        observeSearchQuery()
    }

    private fun observeSearchQuery() {
        publishSubject
            .debounce(1000, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { searchText ->
                _clear.value = Event(Unit)
                if (searchText.isNullOrBlank().not()) {
                    fetchKeyword(searchText)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun createSearchLiveData(): LiveData<PagedList<SearchImage>> {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(30)
            .setPageSize(30)
            .setPrefetchDistance(10)
            .build()

        val factory = object : DataSource.Factory<Int, SearchImage>() {
            override fun create(): DataSource<Int, SearchImage> {
                return SearchDataSource(
                    { searchText.value ?: "" },
                    searchRepository,
                    compositeDisposable,
                    loadState
                ).also {
                    searchDataSource = it
                }
            }
        }

        return LivePagedListBuilder(
            factory,
            config
        ).build()
    }

    private fun fetchKeyword(query: String) {
        searchText.value = query
        searchDataSource?.invalidate()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
