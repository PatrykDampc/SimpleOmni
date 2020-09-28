package com.dampc.simpleomni.repository.networking

import com.dampc.simpleomni.BuildConfig.BASE_URL
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiFactory @Inject constructor() {

    private val rxJava2CallFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    private val gsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    val createApi: OmniApi by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)

        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .build()
            .create(OmniApi::class.java)
    }

}