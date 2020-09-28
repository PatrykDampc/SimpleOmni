package com.dampc.simpleomni.presentation.model

import androidx.databinding.ObservableField

class InputModel(
    val error: ObservableField<String> = ObservableField(),
    val hint: String,
    val text: ObservableField<String> = ObservableField(),
    var onSearchAction: (String) -> Unit = {}
)

class Button(
    val text: String = "",
    var onClick: () -> Unit = {}
)

class Alert(
    val title: String = "",
    val message: String,
    val positiveButton: Button
)