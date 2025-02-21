package com.example.movie2you.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.movie2you.viewmodel.ApiNetworkError
import com.example.movie2you.viewmodel.ApiViewModel

@Composable
fun ShowErrorDialog(
    error: ApiNetworkError,
    viewModel: ApiViewModel
) {
    val errorMessage = when (error) {
        is ApiNetworkError.NetworkError -> error.message
        is ApiNetworkError.ApiError -> error.message
        is ApiNetworkError.UnknownError -> error.message
    }

    AlertDialog(
        onDismissRequest = { },
        title = { Text("Erro") },
        text = { Text(errorMessage) },
        confirmButton = {
            TextButton(onClick = {
                viewModel.getDiscoverMovies()
            }
            ) {
                Text("OK")
            }
        }
    )
}