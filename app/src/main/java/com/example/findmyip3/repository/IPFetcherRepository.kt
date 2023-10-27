package com.example.findmyip3.repository

import android.util.Log
import com.example.findmyip3.network.NetworkClient
import com.example.findmyip3.viewmodel.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IPFetcherRepository: IRepository {


    override suspend fun getApiData(): Flow<UIState> {
        return flow {
            emit(UIState.Loading())

            val response = NetworkClient.api.getIpDetails()
            if (response.isSuccessful) {
                Log.e("Cary", "response.isSuccessful ${response.isSuccessful}")
                response.body()?.let {
                    emit(UIState.Loading(false))
                    emit(UIState.Success(it))
                } ?: kotlin.run {
                    emit(UIState.Loading(false))
                    emit(UIState.Failure(response.message()))
                }
                Log.e("Cary", "response.body() ${response.body()}")
            }
            else {
                emit(UIState.Loading(false))
                emit(UIState.Failure(response.raw().toString()))
                Log.e("Cary", "else scenario ${response.errorBody().toString()}")
            }
        }
    }
}