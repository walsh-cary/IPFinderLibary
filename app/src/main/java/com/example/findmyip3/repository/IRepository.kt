package com.example.findmyip3.repository

import com.example.findmyip3.model.IPFetcherResponseModel
import com.example.findmyip3.viewmodel.UIState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getApiData(): Flow<UIState>
}