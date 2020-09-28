package com.dampc.simpleomni.presentation

import com.dampc.simpleomni.presentation.model.Content

interface Navigator {

    fun goTo(destination: Destination)

    fun goBack()

    sealed class Destination {
        data class Detail(val detail: Content) : Destination()
    }

}