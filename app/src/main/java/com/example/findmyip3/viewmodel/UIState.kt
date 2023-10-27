package com.example.findmyip3.viewmodel

import com.example.findmyip3.model.IPFetcherResponseModel

sealed class UIState{
    data class Success(val data: IPFetcherResponseModel): UIState()
    data class Failure(val error: String): UIState()
    data class Loading(val isLoading: Boolean = true): UIState()
    object Empty: UIState()
}
