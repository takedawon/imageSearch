package com.lanic.brandi.data.repository

import com.lanic.brandi.data.response.SearchResponse
import com.lanic.brandi.network.SearchService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) :
    SearchRepository {

    override fun getSearchImage(query: String, page: String, size: String): Single<SearchResponse> {
        return searchService.getSearchImage(query, page, size)
    }
}

interface SearchRepository {
    fun getSearchImage(query: String, page: String, size: String): Single<SearchResponse>
}