package com.example.movie2you.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie2you.data.api.ApiRepository
import com.example.movie2you.data.api.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ApiState(
    val nowPlaying: List<Movie> = emptyList(),
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
                val response = apiRepository.discoverMovies()
                _uiState.update { currentState ->
                    currentState.copy(
                        nowPlaying = response.results
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
            // TODO não esquecer de deletar
//            log()
        }
    }

    // TODO não esquecer de deletar
    private fun log() {
        Log.d("ApiViewModel", "uiState: ${_uiState.value}")
        Log.d("ApiViewModel", "uiState: ${uiState.value}")
    }
}

