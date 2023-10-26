package com.example.findmyip3.network

import com.example.findmyip3.model.IPFetcherResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface NetworkRequest {
    @GET(NetworkConstants.API_ENDPOINT)
    fun getIpDetails(): IPFetcherResponseModel
}