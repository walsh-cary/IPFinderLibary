package com.example.findmyip3.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmyip3.model.IPFetcherResponseModel
import com.example.findmyip3.network.NetworkConstants.BASE_URL
import com.example.findmyip3.repository.IPFetcherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Error

class IPFetcherViewModel: ViewModel() {
    private val _ipDetailsResponse = MutableStateFlow(IPFetcherResponseModel())
    val ipDetailsResponse: StateFlow<IPFetcherResponseModel> = _ipDetailsResponse

    init {
        initNetworkCall()
    }

    fun updateLiveData(model: IPFetcherResponseModel) {
        this._ipDetailsResponse.value = model
    }

    private fun initNetworkCall() {
        val repository = IPFetcherRepository(this)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.initRetrofit(BASE_URL)
                } catch (e: Error){
                    Log.e("Api IP Details Fetch error!", e.toString())
                }
            }
        }
    }
}