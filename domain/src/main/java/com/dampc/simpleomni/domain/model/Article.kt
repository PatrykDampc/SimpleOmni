package com.dampc.simpleomni.domain.model

data class Article(
    val title: String,
    val imageId: String?,
    val paragraphs: List<String?>?
)