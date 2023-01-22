package com.example.skymoviesapp.data

import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {

    @GET("759fdfa82d6f33522e11")
    suspend fun getMoviesList(): Response<Movies>

}