package com.example.findmyip3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.findmyip3.repository.IRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IPFetcherViewModel(private val repository: IRepository): ViewModel()  {
    private val _ipResponseState = MutableStateFlow<UIState>(UIState.Empty)
    val ipResponseState: StateFlow<UIState> get() = _ipResponseState

    init {
        fetchIpDetails()
    }

    private fun fetchIpDetails(retryCount: Int = 0) {
        viewModelScope.launch {
            repository.getApiData().collect {
                _ipResponseState.value = it
            }
        }
    }
}

class IPFetcherViewModelFactory(private val repository: IRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IPFetcherViewModel::class.java)) {
            return IPFetcherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}