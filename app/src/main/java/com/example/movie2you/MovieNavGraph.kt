package com.example.movie2you

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movie2you.ui.DetailScreen
import com.example.movie2you.ui.MovieApp
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
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
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