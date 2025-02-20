package com.example.movie2you.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie2you.data.api.ApiRepository
import com.example.movie2you.data.api.Movie
import com.example.movie2you.data.api.MovieDetailsResponse
import com.example.movie2you.data.api.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ApiState(
    val nowPlaying: List<Movie> = emptyList(),
    val topRated: List<Movie> = emptyList(),
    val upcoming: List<Movie> = emptyList(),
    val popular: List<Movie> = emptyList(),
    val movieDetails: MovieDetailsResponse? = null,
    val similarMovies: List<Movie> = emptyList(),
    val movieReviews: List<Review> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState())
    val uiState: StateFlow<ApiState> = _uiState.asStateFlow()

    init {
        getDiscoverMovies()
    }

    private fun getDiscoverMovies() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                val responseNowPlaying = apiRepository.getNowPlaying()
                val responseTopRated = apiRepository.getTopRated()
                val responseUpcoming = apiRepository.getUpcoming()
                val responsePopular = apiRepository.getPopular()
                _uiState.update { currentState ->
                    currentState.copy(
                        nowPlaying = responseNowPlaying.results,
                        topRated = responseTopRated.results,
                        upcoming = responseUpcoming.results,
                        popular = responsePopular.results
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        error = "Erro ao obter filmes: ${e.message}"
                    )
                }
            } finally {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                val responseMovieDetails = apiRepository.getMovieDetails(movieId)
                val responseSimilarMovies = apiRepository.getSimilarMovies(movieId)
                val responseMovieReviews = apiRepository.getMovieReviews(movieId)
                _uiState.update { currentState ->
                    currentState.copy(
                        movieDetails = responseMovieDetails,
                        similarMovies = responseSimilarMovies.results,
                        movieReviews = responseMovieReviews.results
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        error = "Erro ao obter filmes: ${e.message}"
                    )
                }
            } finally {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}

