package com.example.movie2you.util

const val BASE_URL = "https://api.themoviedb.org/3/"

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"

fun String.buildImageUrl() : String {
    return IMAGE_BASE_URL + this
}