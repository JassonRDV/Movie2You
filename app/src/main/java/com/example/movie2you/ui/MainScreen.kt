package com.example.movie2you.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.movie2you.R
import com.example.movie2you.data.model.Movie
import com.example.movie2you.ui.components.MoviesByCategory
import com.example.movie2you.viewmodel.ApiState
import com.example.movie2you.viewmodel.ApiViewModel

@Composable
fun MovieApp(
    viewModel: ApiViewModel,
    uiState: ApiState,
    navController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
        )
        MainScreen(
            viewModel = viewModel,
            uiState = uiState,
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp)
        )
    }
}

@Composable
fun MainScreen(
    viewModel: ApiViewModel,
    uiState: ApiState,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            MovieList(
                typeList = "Em Exibição",
                uiState = uiState.nowPlaying,
                viewModel = viewModel,
                navController = navController
            )
        }
        item {
            MovieList(
                uiState = uiState.upcoming,
                viewModel = viewModel,
                typeList = "Em Breve",
                navController = navController
            )
        }
        item {
            MovieList(
                uiState = uiState.popular,
                viewModel = viewModel,
                typeList = "Em Populares",
                navController = navController
            )
        }
        item {
            MovieList(
                uiState = uiState.topRated,
                viewModel = viewModel,
                typeList = "Melhores Avaliados",
                navController = navController
            )
        }
    }
}

@Composable
private fun MovieList(
    uiState: List<Movie>,
    viewModel: ApiViewModel,
    typeList: String,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = typeList,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        MoviesByCategory(uiState, viewModel, navController)
    }
}

