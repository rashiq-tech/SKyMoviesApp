package com.example.skymoviesapp.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skymoviesapp.data.Movies
import com.example.skymoviesapp.utils.ApiState
import com.example.skymoviesapp.utils.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val moviesList: MutableLiveData<ApiState<Movies>> = MutableLiveData()

    fun fetchAllMovies() {
        moviesList.postValue(ApiState.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = homeRepository.fetchAllMovieList()
                    moviesList.postValue(ApiState.Success(response.body()!!))
                } else
                    moviesList.postValue(ApiState.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> moviesList.postValue(ApiState.Error("Network Failure " + ex.localizedMessage))
                    else -> moviesList.postValue(ApiState.Error("Conversion Error"))
                }
            }
        }
    }
}