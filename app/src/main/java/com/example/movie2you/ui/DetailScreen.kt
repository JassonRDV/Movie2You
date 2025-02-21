package com.example.movie2you.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movie2you.R
import com.example.movie2you.ui.components.MoviesByCategory
import com.example.movie2you.viewmodel.ApiState
import com.example.movie2you.viewmodel.ApiViewModel
import java.util.Locale

@Composable
fun DetailScreen(
    viewModel: ApiViewModel,
    uiState: ApiState,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
    ) {
        MovieBanner(
            uiState = uiState,
            navController = navController,
            modifier = Modifier
                .weight(0.8f)
        )
        MovieDetail(
            uiState = uiState,
            viewModel = viewModel,
            navController = navController,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun MovieBanner(
    uiState: ApiState,
    modifier: Modifier,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 28.dp,
                    bottomEnd = 28.dp
                )
            )
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.voltar),
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )
        }
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original/${uiState.movieDetails?.backdropPath}",
            contentDescription = "Imagem de capa",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = uiState.movieDetails?.title ?: stringResource(R.string.titulo),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                val runtimeInMinutes = uiState.movieDetails?.runtime ?: 0
                val hours = runtimeInMinutes / 60
                val minutes = runtimeInMinutes % 60
                val formattedRuntime = when {
                    hours > 0 && minutes > 0 -> "$hours hora(s) $minutes minuto(s)"
                    hours > 0 -> "$hours hora(s)"
                    minutes > 0 -> "$minutes minuto(s)"
                    else -> "Tempo Indisponível"
                }
                Text(
                    text = formattedRuntime,
                    color = Color.White,
                    fontSize = 12.sp
                )
                val genres =
                    uiState.movieDetails?.genres?.joinToString(separator = " • ") { it.name }
                        ?: stringResource(R.string.generos_indisponiveis)
                Text(
                    text = genres,
                    color = Color.White,
                    fontSize = 12.sp

                )
                Text(
                    text =
                    String.format(
                        Locale.US,
                        "⭐ %.1f / 10 Média de Votos",
                        uiState.movieDetails?.voteAverage ?: 0.0
                    ),
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
            Card(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(50.dp)
                    .weight(1f)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/original/${uiState.movieDetails?.posterPath}",
                    contentDescription = stringResource(R.string.imagem_de_capa),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(0.783f)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun MovieDetail(
    uiState: ApiState,
    viewModel: ApiViewModel,
    navController: NavHostController,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        item {
            Synopsis(uiState)
        }
        item {
            Comments(uiState)
        }
        item {
            MoreLikeThis(uiState, viewModel, navController)
        }
    }
}

@Composable
private fun Synopsis(uiState: ApiState) {
    Text(
        text = stringResource(R.string.sinopse),
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 10.dp)
    )
    Text(
        text = uiState.movieDetails?.overview ?: stringResource(R.string.sinopse_indisponivel),
        color = Color.White,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(vertical = 20.dp)
    )
}

@Composable
private fun Comments(uiState: ApiState) {
    Text(
        text = stringResource(R.string.comentarios),
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 20.dp)
    )
    Box(
        modifier = Modifier
            .border(
                1.dp,
                color = Color(0XFF000000),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
    ) {
        LazyColumn(
            modifier = Modifier
                .aspectRatio(0.9f)
                .background(color = Color(0XFF242135))
                .clip(RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            items(uiState.movieReviews.size) { item ->
                val review = uiState.movieReviews[item]
                Column(
                    modifier = Modifier
                        .padding(
                            bottom = 20.dp
                        )
                ) {
                    Text(
                        text = review.author,
                        color = Color.White,
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp
                            )
                    )
                    Text(
                        text = review.content,
                        color = Color.LightGray,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp
                            )
                    )
                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun MoreLikeThis(
    uiState: ApiState,
    viewModel: ApiViewModel,
    navController: NavHostController
) {
    Text(
        text = stringResource(R.string.mais_como_este),
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 20.dp)
    )
    MoviesByCategory(
        uiState = uiState.similarMovies,
        viewModel = viewModel,
        navController = navController
    )
}