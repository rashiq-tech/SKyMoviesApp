package com.example.skymoviesapp.ui.feature.movies.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skymoviesapp.data.models.Movies
import com.example.skymoviesapp.ui.feature.movies.repository.HomeRepository
import com.example.skymoviesapp.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    val moviesList: MutableLiveData<ApiState<List<Movies>>> = MutableLiveData()

    fun fetchAllMovies() {
        moviesList.postValue(ApiState.Loading())
        viewModelScope.launch {
            try {
                    val response = homeRepository.fetchAllMovieList()
                    moviesList.postValue(ApiState.Success(response.body()!!))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> moviesList.postValue(ApiState.Error("Network Failure " + ex.localizedMessage))
                    is NetworkErrorException -> moviesList.postValue(ApiState.Error("No Internet Connection.."))
                    is NullPointerException -> moviesList.postValue(ApiState.Error("No data available in cache.."))
                    else -> moviesList.postValue(ApiState.Error("Conversion Error"))
                }
            }
        }
    }
}