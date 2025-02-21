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
    val error: ApiNetworkError? = null
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

    private fun <T> safeApiCall(action: suspend () -> T, onSuccess: (T) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val result = action()
                onSuccess(result)
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
        safeApiCall({
            coroutineScope {
                val nowPlaying = async { apiRepository.getNowPlaying() }
                val topRated = async { apiRepository.getTopRated() }
                val upcoming = async { apiRepository.getUpcoming() }
                val popular = async { apiRepository.getPopular() }

                ApiState(
                    nowPlaying = nowPlaying.await().results,
                    topRated = topRated.await().results,
                    upcoming = upcoming.await().results,
                    popular = popular.await().results
                )
            }
        }) { newState ->
            _uiState.value = newState
        }
    }

    fun getMovieDetails(movieId: Int) {
        safeApiCall({
            coroutineScope {
                val movieDetails = async { apiRepository.getMovieDetails(movieId) }
                val similarMovies = async { apiRepository.getSimilarMovies(movieId) }
                val movieReviews = async { apiRepository.getMovieReviews(movieId) }

                ApiState(
                    movieDetails = movieDetails.await(),
                    similarMovies = similarMovies.await().results,
                    movieReviews = movieReviews.await().results
                )
            }
        }) { newState ->
            _uiState.value = newState
        }
    }
}