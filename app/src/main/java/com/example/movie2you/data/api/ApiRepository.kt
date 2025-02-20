package com.example.movie2you.data.api

import javax.inject.Inject

interface ApiRepository {

    suspend fun getNowPlaying(): MovieResponse

    suspend fun getTopRated(): MovieResponse

    suspend fun getUpcoming(): MovieResponse

    suspend fun getPopular(): MovieResponse

    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse

    suspend fun getSimilarMovies(movieId: Int): MovieResponse

    suspend fun getMovieReviews(movieId: Int): ReviewResponse

}

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