package com.example.movie2you.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results") val results: List<Movie>,
)

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String,
)

data class MovieDetailsResponse(
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("id") val id: Int,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
)

data class Genre(
    @SerializedName("name") val name: String
)

data class ReviewResponse(
    @SerializedName("results") val results: List<Review>,
)

data class Review(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
)