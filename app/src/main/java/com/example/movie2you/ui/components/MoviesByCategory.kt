package com.example.movie2you.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movie2you.MovieDestinations
import com.example.movie2you.data.model.Movie
import com.example.movie2you.util.buildImageUrl
import com.example.movie2you.viewmodel.ApiViewModel

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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(4.dp)
                    .clickable(
                        onClick = {
                            viewModel.getMovieDetails(movie.id)
                            navController.navigate(MovieDestinations.DETAIL_ROUTE)
                        }
                    )
            ) {
                AsyncImage(
                    model = movie.posterPath?.buildImageUrl(),
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