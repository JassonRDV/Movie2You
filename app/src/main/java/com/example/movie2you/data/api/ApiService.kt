package com.example.movie2you.data.api

import com.example.movie2you.data.model.MovieDetailsResponse
import com.example.movie2you.data.model.MovieResponse
import com.example.movie2you.data.model.ReviewResponse
import retrofit2.http.Path
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getNowPlaying(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_release_type") withReleaseType: String = "2|3",
        @Query("release_date.gte") minDate: String = "2025-01-15",
        @Query("release_date.lte") maxDate: String = "2025-02-26"
    ) : MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_release_type") withReleaseType: String = "2|3",
        @Query("release_date.gte") minDate: String = "2025-01-15",
        @Query("release_date.lte") maxDate: String = "2025-02-26"
    ) : MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_release_type") withReleaseType: String = "2|3",
        @Query("release_date.gte") minDate: String = "2025-01-15",
        @Query("release_date.lte") maxDate: String = "2025-02-26"
    ) : MovieResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_release_type") withReleaseType: String = "2|3",
        @Query("release_date.gte") minDate: String = "2025-01-15",
        @Query("release_date.lte") maxDate: String = "2025-02-26"
    ) : MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
    ) : MovieDetailsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ) : MovieResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ) : ReviewResponse
}