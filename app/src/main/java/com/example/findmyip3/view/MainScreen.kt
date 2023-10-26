package com.example.findmyip3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.findmyip3.viewmodel.IPFetcherViewModel

@Composable
fun MainScreen(viewModel: IPFetcherViewModel) {
    val ipDetailsResponse by viewModel.ipDetailsResponse.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = ipDetailsResponse.toString())
    }

    viewModel.onViewCreated()
}