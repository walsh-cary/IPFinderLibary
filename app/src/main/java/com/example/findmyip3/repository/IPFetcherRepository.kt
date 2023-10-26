package com.example.findmyip3.repository

import com.example.findmyip3.model.IPFetcherResponseModel
import com.example.findmyip3.network.NetworkRequest
import com.example.findmyip3.viewmodel.IPFetcherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IPFetcherRepository(val viewModel: IPFetcherViewModel) {
    private var interceptor: HttpLoggingInterceptor? = null
    private var client: OkHttpClient? = null

    suspend fun initRetrofit(
        baseUrl: String
    ) {
        interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder().apply {
            interceptor?.let { this.addInterceptor(it) }
        }.build()

        client?.let {
            getApi(baseUrl, it).getIpDetails()
                .enqueue(
                    object : Callback<IPFetcherResponseModel> {
                        override fun onResponse(
                            call: Call<IPFetcherResponseModel>,
                            response: Response<IPFetcherResponseModel>
                        ) {
                            response.body()?.let { viewModel.updateLiveData(it) }
                        }

                        override fun onFailure(call: Call<IPFetcherResponseModel>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    }
                )
        }
    }

    private fun getApi(baseUrl: String, client: OkHttpClient): NetworkRequest =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
            .create(NetworkRequest::class.java)
}