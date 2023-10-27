package com.example.findmyip3.network

import com.example.findmyip3.model.IPFetcherResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkRequest {
    @GET(NetworkConstants.API_ENDPOINT)
    suspend fun getIpDetails(): Response<IPFetcherResponseModel>
}