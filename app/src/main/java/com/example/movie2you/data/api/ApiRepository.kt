package com.example.movie2you.data.api

import com.example.movie2you.data.model.MovieDetailsResponse
import com.example.movie2you.data.model.MovieResponse
import com.example.movie2you.data.model.ReviewResponse

interface ApiRepository {

    suspend fun getNowPlaying(): MovieResponse

    suspend fun getTopRated(): MovieResponse

    suspend fun getUpcoming(): MovieResponse

    suspend fun getPopular(): MovieResponse

    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse

    suspend fun getSimilarMovies(movieId: Int): MovieResponse

    suspend fun getMovieReviews(movieId: Int): ReviewResponse

}

