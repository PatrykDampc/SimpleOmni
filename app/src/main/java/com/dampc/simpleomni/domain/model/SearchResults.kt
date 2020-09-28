package com.dampc.simpleomni.domain.model

data class SearchResults(
    val articles: List<Article>,
    val topics: List<Topic>
)