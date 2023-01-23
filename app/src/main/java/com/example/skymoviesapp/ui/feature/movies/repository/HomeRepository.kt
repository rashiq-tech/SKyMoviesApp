package com.example.skymoviesapp.ui.feature.movies.repository

import com.example.skymoviesapp.data.models.Movies
import com.example.skymoviesapp.data.feature.movies.HomeApiService
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