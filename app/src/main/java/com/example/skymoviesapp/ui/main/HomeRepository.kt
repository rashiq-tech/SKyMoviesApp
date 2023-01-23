package com.example.skymoviesapp.ui.main

import com.example.skymoviesapp.data.Movies
import com.example.skymoviesapp.data.MoviesResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val homeApiService: HomeApiService) {

    suspend fun fetchAllMovieList(): Response<List<Movies>> = withContext(
        Dispatchers.IO) {
        homeApiService.getMoviesList()
    }

}