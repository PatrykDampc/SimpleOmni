package com.dampc.simpleomni

import com.dampc.simpleomni.repository.networking.ApiFactory
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ApiTest {

    private val api = ApiFactory().createApi

    @Test
    fun search() {
        val results = api.search("trump").blockingGet()
        assertThat(results).isNotNull()
    }

}