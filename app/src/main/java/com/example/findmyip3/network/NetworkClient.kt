package com.example.findmyip3.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private var interceptor: HttpLoggingInterceptor? = null
    private var client: OkHttpClient? = null

    val api: NetworkRequest by lazy {
        initRetrofit(NetworkConstants.BASE_URL)
    }

    private fun initRetrofit(
        baseUrl: String
    ):NetworkRequest {
        interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder().apply {
            interceptor?.let { this.addInterceptor(it) }
        }.build()

        client?.let {
            return getApi(baseUrl, it)
        } ?: throw Exception("Fail to create Network client")
    }

    private fun getApi(baseUrl: String, client: OkHttpClient): NetworkRequest =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
            .create(NetworkRequest::class.java)
}