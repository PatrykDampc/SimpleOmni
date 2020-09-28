package com.dampc.simpleomni.repository.model

import com.dampc.simpleomni.domain.model.Article
import com.dampc.simpleomni.domain.model.SearchResults
import com.dampc.simpleomni.domain.model.Topic
import javax.inject.Inject

class SearchResultsMapper @Inject constructor() {

    fun mapToDomain(response: SearchResponse): SearchResults {
        return SearchResults(
            articles = response.articles.map {
                Article(
                    title = it.title.value,
                    imageId = it.mainResource?.imageAsset?.id,
                    paragraphs = it.mainText.paragraphs?.map {
                        it.text?.value
                    }
                )
            },
            topics = response.topics.map {
                Topic(
                    title = it.title,
                    type = it.type
                )
            }
        )
    }

}