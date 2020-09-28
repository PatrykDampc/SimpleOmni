package com.dampc.simpleomni.domain

import com.dampc.simpleomni.domain.model.Content

interface Navigator {

    fun goTo(destination: Destination)

    fun goBack()

    sealed class Destination {
        data class Detail(val detail: Content) : Destination()
    }

}