package com.lanic.image.network

import com.lanic.image.data.response.SearchResponse
import com.lanic.image.util.Const
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {

    @GET("/v2/search/image")
    fun getSearchImage(
        @Query("query") query: String,
        @Query("page") page: String,
        @Query("size") size: String,
        @Header("Authorization") authorization: String = Const.KAKAO_KEY
    ): Single<SearchResponse>
}
