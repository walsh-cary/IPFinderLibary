package com.example.findmyip3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.findmyip3.ui.theme.FindMyIP3Theme
import com.example.findmyip3.view.MainScreen
import com.example.findmyip3.viewmodel.IPFetcherViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = IPFetcherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewCreated()
        setContent {
            FindMyIP3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(this.viewModel)
                }
            }
        }
    }
}