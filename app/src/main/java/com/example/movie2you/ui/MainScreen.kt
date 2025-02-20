package com.example.movie2you.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movie2you.MovieDestinations
import com.example.movie2you.R
import com.example.movie2you.data.api.Movie
import com.example.movie2you.viewmodel.ApiState
import com.example.movie2you.viewmodel.ApiViewModel

@Composable
fun MovieApp(
    viewModel: ApiViewModel,
    uiState: ApiState,
    navController: NavHostController
) {
    Scaffold(
        containerColor = Color(color = 0XFF1C1A29),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.movie2you_foreground),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(200.dp)
            )
            MainScreen(
                viewModel = viewModel,
                uiState = uiState,
                navController = navController
            )
        }
    }
}

@Composable
fun MainScreen(
    viewModel: ApiViewModel,
    uiState: ApiState,
    navController: NavHostController
) {

    LazyColumn (
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(
            start = 20.dp,
            top = 97.dp
        )
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
            modifier = Modifier
                .padding(top = 16.dp)
        )
        MoviesByCategory(uiState, viewModel, navController)
    }
}

@Composable
fun MoviesByCategory(
    uiState: List<Movie>,
    viewModel: ApiViewModel,
    navController: NavHostController
) {
    LazyRow(
        modifier = Modifier
    ) {
        items(
            uiState
        ) { movie ->
            val imageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(4.dp)
                    .clickable(
                        onClick = {
                            viewModel.getMovieDetails(movie.id)
                            navController.navigate(MovieDestinations.DETAIL_ROUTE)
                        }
                    )
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f / 3f),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}