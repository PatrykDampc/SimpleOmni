package com.dampc.simpleomni.repository.networking

import com.dampc.simpleomni.repository.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OmniApi {

    @GET("search")
    fun search(@Query("query") query: String): Single<SearchResponse>

}