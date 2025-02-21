package com.example.movie2you

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie2you.ui.DetailScreen
import com.example.movie2you.ui.MovieApp
import com.example.movie2you.ui.components.ShowErrorDialog
import com.example.movie2you.viewmodel.ApiViewModel

object MovieDestinations {
    const val MAIN_ROUTE = "main"
    const val DETAIL_ROUTE = "detail"
}

@Composable
fun MovieNavGraph(
    viewModel: ApiViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    startDestination: String = MovieDestinations.MAIN_ROUTE,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()

    uiState.error?.let { error ->
        ShowErrorDialog(
            error = error,
            viewModel = viewModel
        )
    }

    Scaffold(
        containerColor = Color(0XFF171424),
        contentColor = Color.Transparent,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
                .padding(innerPadding)
                .background(Color(0XFF1C1A29))
        ) {
            composable(
                route = MovieDestinations.MAIN_ROUTE
            ) {
                MovieApp(
                    viewModel = viewModel,
                    uiState = uiState,
                    navController = navController
                )
            }
            composable(
                route = MovieDestinations.DETAIL_ROUTE
            ) {
                DetailScreen(
                    viewModel = viewModel,
                    uiState = uiState,
                    navController = navController
                )
            }
        }
    }
}

