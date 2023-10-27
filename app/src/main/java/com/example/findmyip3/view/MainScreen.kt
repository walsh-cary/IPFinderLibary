package com.example.findmyip3.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.findmyip3.model.IPFetcherResponseModel
import com.example.findmyip3.viewmodel.IPFetcherViewModel
import com.example.findmyip3.viewmodel.UIState

@Composable
fun DisplayIPDetails(viewModel: IPFetcherViewModel) {
    val uiState = viewModel.ipResponseState.collectAsState().value
    Log.e("Cary", "DisplayIPDetails ${viewModel.ipResponseState.collectAsState().value}")
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            is UIState.Loading -> {
                CircularProgressIndicator()
            }
            is UIState.Success -> {
                DisplaySuccess(ipResponse = uiState.data)
            }
            is UIState.Failure -> DisplayError(uiState.error)

            is UIState.Empty -> DisplayError("Empty Response")
        }
    }
}

@Composable
fun DisplayError(errorMessage: String) {
    Text(text = errorMessage, color = Color.Red, textAlign = TextAlign.Center)
}

@Composable
fun DisplaySuccess(ipResponse: IPFetcherResponseModel) {
    Column {
        Text(text = "Your City: ${ipResponse.city}", textAlign = TextAlign.Center)
        Text(text = "Your Country: ${ipResponse.country}", textAlign = TextAlign.Center)
        Text(text = "Your Region: ${ipResponse.region}", textAlign = TextAlign.Center)
    }
}