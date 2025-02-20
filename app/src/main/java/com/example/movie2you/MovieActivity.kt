package com.example.movie2you

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.movie2you.ui.theme.Movie2youTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Movie2youTheme {
                MovieNavGraph(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

