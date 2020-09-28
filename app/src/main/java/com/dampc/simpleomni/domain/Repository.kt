package com.dampc.simpleomni.domain

import com.dampc.simpleomni.domain.model.SearchResults
import io.reactivex.Single

interface Repository {

    fun search(query: String): Single<SearchResults>

}