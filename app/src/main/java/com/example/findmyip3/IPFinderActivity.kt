package com.example.findmyip3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.findmyip3.repository.IPFetcherRepository
import com.example.findmyip3.repository.IRepository
import com.example.findmyip3.ui.theme.FindMyIP3Theme
import com.example.findmyip3.view.DisplayIPDetails
import com.example.findmyip3.viewmodel.IPFetcherViewModel
import com.example.findmyip3.viewmodel.IPFetcherViewModelFactory

class IPFinderActivity : ComponentActivity() {
    private val repository: IRepository by lazy {
        IPFetcherRepository()
    }
    private val viewmodel: IPFetcherViewModel by viewModels {
        IPFetcherViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindMyIP3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayIPDetails(viewmodel)
                }
            }
        }
    }
}