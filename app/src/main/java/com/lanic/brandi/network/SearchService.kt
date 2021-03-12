package com.lanic.brandi.network

import com.lanic.brandi.data.response.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/v2/search/image")
    fun getSearchImage(
        @Query("query") query: String,
        @Query("page") page: String,
        @Query("size") size: String,
    ): Single<SearchResponse>

}