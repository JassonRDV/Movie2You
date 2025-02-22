package com.example.movie2you.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie2you.data.api.ApiRepository
import com.example.movie2you.data.model.Movie
import com.example.movie2you.data.model.MovieDetailsResponse
import com.example.movie2you.data.model.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

sealed class ApiNetworkError {
    data class NetworkError(val message: String) : ApiNetworkError()
    data class ApiError(val message: String) : ApiNetworkError()
    data class UnknownError(val message: String) : ApiNetworkError()
}

data class ApiState(
    val nowPlaying: List<Movie> = emptyList(),
    val topRated: List<Movie> = emptyList(),
    val upcoming: List<Movie> = emptyList(),
    val popular: List<Movie> = emptyList(),
    val movieDetails: MovieDetailsResponse? = null,
    val similarMovies: List<Movie> = emptyList(),
    val movieReviews: List<Review> = emptyList(),
    val isLoading: Boolean = false,
    val error: ApiNetworkError? = null,
    val formattedRuntime: String = "",
    val movieGenres: String = ""
)

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ApiState())
    val uiState: StateFlow<ApiState> = _uiState.asStateFlow()

    init {
        getDiscoverMovies()
    }

    private fun safeApiCall(action: suspend () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                action()
            } catch (e: Exception) {
                val apiError = when (e) {
                    is IOException -> ApiNetworkError.NetworkError("Erro de conexão: ${e.message}")
                    is HttpException -> ApiNetworkError.ApiError("Erro na API: ${e.message}")
                    else -> ApiNetworkError.UnknownError("Erro desconhecido: ${e.message}")
                }
                _uiState.update { it.copy(error = apiError) }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun getDiscoverMovies() {
        safeApiCall {
            coroutineScope {
                val nowPlaying = async { apiRepository.getNowPlaying() }
                val topRated = async { apiRepository.getTopRated() }
                val upcoming = async { apiRepository.getUpcoming() }
                val popular = async { apiRepository.getPopular() }

                _uiState.update {
                    it.copy(
                        nowPlaying = nowPlaying.await().results,
                        topRated = topRated.await().results,
                        upcoming = upcoming.await().results,
                        popular = popular.await().results
                    )
                }
            }
        }
    }

    fun getMovieDetails(movieId: Int) {
        safeApiCall {
            coroutineScope {
                val movieDetails = async { apiRepository.getMovieDetails(movieId) }
                val similarMovies = async { apiRepository.getSimilarMovies(movieId) }
                val movieReviews = async { apiRepository.getMovieReviews(movieId) }

                _uiState.update {
                    it.copy(
                        movieDetails = movieDetails.await(),
                        similarMovies = similarMovies.await().results,
                        movieReviews = movieReviews.await().results
                    )
                }
                formattedRuntime()
                movieGenres()
            }
        }
    }

    private fun formattedRuntime() {
        val runtimeInMinutes = _uiState.value.movieDetails?.runtime ?: 0
        val hours = runtimeInMinutes / 60
        val minutes = runtimeInMinutes % 60
        val formattedRuntime = when {
            hours > 0 && minutes > 0 -> "$hours hora(s) $minutes minuto(s)"
            hours > 0 -> "$hours hora(s)"
            minutes > 0 -> "$minutes minuto(s)"
            else -> "Tempo Indisponível"
        }
        _uiState.update {
            it.copy(
                formattedRuntime = formattedRuntime
            )
        }
    }

    private fun movieGenres() {
        val genres =
            uiState.value.movieDetails?.genres?.joinToString(separator = " • ") { it.name }
                ?: ""

        _uiState.update {
            it.copy(
                movieGenres = genres
            )
        }
    }
}