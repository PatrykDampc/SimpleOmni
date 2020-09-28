package com.dampc.simpleomni.domain

import com.dampc.simpleomni.domain.model.SearchResults
import io.reactivex.Single
import javax.inject.Inject

class SearchService @Inject constructor(
    private val repository: Repository
) {

    private val imagesBaseUrl = "https://gfx-android.omni.se/images/"

    fun search(query: String): Single<SearchResults> {
        return repository.search(query)
    }

    fun generateUrlForImage(id: String?): String? {
        return id?.let {
            imagesBaseUrl + id
        }
    }

}