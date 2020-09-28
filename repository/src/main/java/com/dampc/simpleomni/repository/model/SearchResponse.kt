package com.dampc.simpleomni.repository.model

data class SearchResponse(
    val articles: List<ArticleResponse>,
    val topics: List<TopicResponse>
)