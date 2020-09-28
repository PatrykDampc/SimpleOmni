package com.dampc.simpleomni.presentation.view

import android.util.Log
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dampc.simpleomni.R
import com.dampc.simpleomni.domain.SearchService
import com.dampc.simpleomni.domain.Strings
import com.dampc.simpleomni.domain.model.Article
import com.dampc.simpleomni.domain.model.SearchResults
import com.dampc.simpleomni.domain.model.Topic
import com.dampc.simpleomni.presentation.Navigator
import com.dampc.simpleomni.presentation.Navigator.Destination.Detail
import com.dampc.simpleomni.presentation.model.*
import com.dampc.simpleomni.presentation.model.MainModel.TabModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

class MainViewModel @ViewModelInject constructor(
    private val searchService: SearchService,
    private val strings: Strings,
    private val navigator: Navigator
) : ViewModel() {

    private val initial = PublishRelay.create<Unit>()
    private val refresh = PublishRelay.create<Unit>()
    private val searchClick = PublishRelay.create<String>()
    private val search = PublishRelay.create<String>()

    private val alertEvent = PublishRelay.create<Alert>()

    private val events = Observable.mergeArray(
        initial.map { Event.Initial },
        refresh.map { Event.Refresh },
        searchClick.map {
            Event.SearchClick(
                it
            )
        },
        search.flatMap { query ->
            searchService.search(query)
                .map {
                    Event.DataDownloaded(
                        it
                    ) as Event
                }
                .onErrorReturn {
                    Event.Error(
                        it
                    )
                }
                .toObservable()
        }
    )

    val model: LiveData<MainModel> by lazy {
        val liveData = MutableLiveData<MainModel>()

        events.scan(initialModel()) { model, event ->
            when (event) {
                is Event.SearchClick -> {
                    model.apply {
                        if (event.query.isBlank()) {
                            input.get()?.error?.set(strings.get(R.string.main_activity_search_error_empty))
                        } else {
                            emptyPlaceholder.set("")
                            tabs.set(emptyList())
                            loading.set(true)
                            search.accept(event.query)
                        }
                    }
                }
                is Event.Error -> {
                    model.apply {
                        tabs.set(emptyList())
                        loading.set(false)
                        alertEvent.accept(
                            Alert(
                                message = strings.get(R.string.error_generic),
                                positiveButton = Button(strings.get(R.string.close)) {
                                    emptyPlaceholder.set(strings.get(R.string.main_activity_initial_empty_state))
                                }
                            )
                        )
                    }
                    Log.d("FETCH_DATA_ERROR", "${event.error}")
                }
                is Event.DataDownloaded -> {
                    model.apply {
                        loading.set(false)
                        emptyPlaceholder.set(strings.get(R.string.main_activity_empty_search_results))
                        tabs.set(setTabs(event.searchResults))
                    }
                }
            }
            model
        }.subscribe { liveData.postValue(it) }

        initial.accept(Unit)
        liveData
    }

    val alert: LiveData<Alert> by lazy {
        val liveData = MutableLiveData<Alert>()
        alertEvent.subscribe { liveData.postValue(it) }
        liveData
    }

    private fun initialModel(): MainModel {
        return MainModel(
            emptyPlaceholder = ObservableField(strings.get(R.string.main_activity_initial_empty_state)),
            input = ObservableField(
                InputModel(
                    hint = strings.get(R.string.main_activity_search_hint),
                    onSearchAction = { searchClick.accept(it) }
                ))
        )
    }

    private fun setTabs(searchResults: SearchResults): List<TabModel> {
        val articles = if (searchResults.articles.isNotEmpty())
            TabModel(
                label = strings.get(R.string.main_activity_articles),
                items = searchResults.articles.map {
                    ArticleItem(
                        Button(it.title) { goToArticleDetail(it) },
                        imageUrl = searchService.generateUrlForImage(it.imageId)
                    )
                }
            ) else null

        val topics = if (searchResults.topics.isNotEmpty())
            TabModel(
                label = strings.get(R.string.main_activity_topics),
                items = searchResults.topics.map {
                    TopicItem(
                        button = Button(it.title) {
                            goToTopicDetail(it)
                        }
                    )
                }
            )
        else null

        return listOfNotNull(articles, topics)
    }

    private fun goToArticleDetail(article: Article) {
        navigator.goTo(
            Detail(
                Content(
                    title = article.title,
                    text = article.paragraphs.parseParagraphs()
                )
            )
        )
    }

    private fun goToTopicDetail(topic: Topic) {
        navigator.goTo(
            Detail(
                Content(
                    title = topic.title,
                    text = topic.type
                )
            )
        )
    }

    private fun List<String?>?.parseParagraphs(): String {
        return this?.filterNotNull()?.joinToString("\n\n") ?: ""
    }

    sealed class Event {
        object Refresh : Event()
        object Initial : Event()

        data class SearchClick(val query: String) : Event()
        data class DataDownloaded(val searchResults: SearchResults) : Event()
        data class Error(val error: Throwable) : Event()
    }
}