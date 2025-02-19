package com.example.movie2you.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movie2you.viewmodel.ApiState
import com.example.movie2you.viewmodel.ApiViewModel

@Composable
fun MainScreen(
    viewModel: ApiViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier.padding(
            start = 20.dp,
            top = 97.dp
        )
    ) {
        MovieList(
            typeList = "Em Exibição",
            uiState = uiState
        )
        MovieList(
            typeList = "Em Breve",
            uiState = uiState
        )
        MovieList(
            typeList = "Em Populares",
            uiState = uiState
        )
        MovieList(
            typeList = "Melhores Avaliados",
            uiState = uiState
        )
    }
}

@Composable
private fun MovieList(
    typeList: String,
    uiState: State<ApiState>
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
        LazyRow(
            modifier = Modifier
        ) {
            items(
                uiState.value.nowPlaying
            ) { movie ->
                val imageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}"
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = movie.title,
                    )
                }
            }
        }
    }
}
