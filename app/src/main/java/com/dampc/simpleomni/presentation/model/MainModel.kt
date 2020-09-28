package com.dampc.simpleomni.presentation.model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

data class MainModel(
    val emptyPlaceholder: ObservableField<String> = ObservableField(),
    val tabs: ObservableField<List<TabModel>> = ObservableField(),
    val input: ObservableField<InputModel> = ObservableField(),
    val loading: ObservableBoolean = ObservableBoolean(),
    val alert: ObservableField<Alert> = ObservableField()

) {

    data class TabModel(
        val label: String,
        val items: List<RecyclerViewItem>
    ) : RecyclerViewItem

}