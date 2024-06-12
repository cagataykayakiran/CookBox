package com.example.recipeapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.recipeapp.presentation.navigation.NavigationGraph
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import com.example.recipeapp.presentation.ui.theme.PaparaFinalCaseTheme
import com.example.recipeapp.util.ConnectivityObserver
import com.example.recipeapp.util.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   private  lateinit var connectivityObserver: ConnectivityObserver
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkHelper(applicationContext)
        enableEdgeToEdge()
        setContent {
            PaparaFinalCaseTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundPrimary,
                ) {
                   NavigationGraph(status = status)
                }
            }
        }
    }
}
