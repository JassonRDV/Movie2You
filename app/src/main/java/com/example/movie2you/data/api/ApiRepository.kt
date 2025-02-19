package com.example.movie2you.data.api

import javax.inject.Inject

interface ApiRepository {

    suspend fun discoverMovies() : MovieResponse

}

class DefaultApiRepository @Inject constructor(
    private val apiService: ApiService,
) : ApiRepository {

    override suspend fun discoverMovies() : MovieResponse =
        apiService.discoverMovies()

}