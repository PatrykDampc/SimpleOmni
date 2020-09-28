package com.dampc.simpleomni.repository

import com.dampc.simpleomni.domain.Repository
import com.dampc.simpleomni.domain.model.SearchResults
import com.dampc.simpleomni.repository.model.SearchResultsMapper
import com.dampc.simpleomni.repository.networking.ApiFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val searchResultsMapper: SearchResultsMapper
) : Repository {

    private val api = ApiFactory().createApi

    override fun search(query: String): Single<SearchResults> {
        return api.search(query)
            .map { searchResultsMapper.mapToDomain(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}