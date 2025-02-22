package com.example.movie2you

import com.example.movie2you.data.api.ApiService
import com.example.movie2you.data.api.DefaultApiRepository
import com.example.movie2you.data.model.Movie
import com.example.movie2you.data.model.MovieResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doThrow

@ExperimentalCoroutinesApi
class DefaultApiRepositoryTest {

    @Mock lateinit var apiService: ApiService
    private lateinit var repository: DefaultApiRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = DefaultApiRepository(apiService)
    }

    @Test
    fun `when getNowPlaying is called, it should return data correctly` () = runTest {

        val fakeResponse = MovieResponse(listOf(Movie(
            id = 1,
            title = "Fake Movie",
            posterPath = "HTTP_PATH"
        )))

        `when`(apiService.getNowPlaying()).thenReturn(fakeResponse)

        val result = repository.getNowPlaying()

        assertEquals(fakeResponse, result)

        verify(apiService, times(1)).getNowPlaying()
    }

    @Test
    fun `when getTopRated is called, it should return data correctly` () = runTest {

        val fakeResponse = MovieResponse(listOf(Movie(
            id = 1,
            title = "Fake Movie",
            posterPath = "HTTP_PATH"
        )))

        `when`(apiService.getTopRated()).thenReturn(fakeResponse)

        val result = repository.getTopRated()

        assertEquals(fakeResponse, result)

        verify(apiService, times(1)).getTopRated()
    }

    @Test
    fun `when getUpcoming is called, it should return data correctly` () = runTest {

        val fakeResponse = MovieResponse(listOf(Movie(
            id = 1,
            title = "Fake Movie",
            posterPath = "HTTP_PATH"
        )))

        `when`(apiService.getUpcoming()).thenReturn(fakeResponse)

        val result = repository.getUpcoming()

        assertEquals(fakeResponse, result)

        verify(apiService, times(1)).getUpcoming()
    }

    @Test
    fun `when getPopular is called, it should return data correctly` () = runTest {

        val fakeResponse = MovieResponse(listOf(Movie(
            id = 1,
            title = "Fake Movie",
            posterPath = "HTTP_PATH"
        )))

        `when`(apiService.getPopular()).thenReturn(fakeResponse)

        val result = repository.getPopular()

        assertEquals(fakeResponse, result)

        verify(apiService, times(1)).getPopular()
    }

}