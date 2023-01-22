package com.example.skymoviesapp.ui.main

import com.example.skymoviesapp.data.HomeApiService
import com.example.skymoviesapp.data.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val homeApiService: HomeApiService) {

    suspend fun fetchAllMovieList(): Response<Movies> = withContext(
        Dispatchers.IO) {
        homeApiService.getMoviesList()
    }

}