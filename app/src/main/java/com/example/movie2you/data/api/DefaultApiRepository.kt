package com.example.movie2you.data.api

import com.example.movie2you.data.model.MovieDetailsResponse
import com.example.movie2you.data.model.MovieResponse
import com.example.movie2you.data.model.ReviewResponse
import javax.inject.Inject

class DefaultApiRepository @Inject constructor(
    private val apiService: ApiService,
) : ApiRepository {

    override suspend fun getNowPlaying(): MovieResponse =
        apiService.getNowPlaying()

    override suspend fun getTopRated(): MovieResponse =
        apiService.getTopRated()

    override suspend fun getUpcoming(): MovieResponse =
        apiService.getUpcoming()

    override suspend fun getPopular(): MovieResponse =
        apiService.getPopular()

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse =
        apiService.getMovieDetails(movieId)

    override suspend fun getSimilarMovies(movieId: Int): MovieResponse =
        apiService.getSimilarMovies(movieId)

    override suspend fun getMovieReviews(movieId: Int): ReviewResponse =
        apiService.getMovieReviews(movieId)

}