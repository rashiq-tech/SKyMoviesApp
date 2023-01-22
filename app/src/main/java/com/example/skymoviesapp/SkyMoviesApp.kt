package com.example.skymoviesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SkyMoviesApp : Application() {

    companion object {
        const val API_ENDPOINT = "https://api.npoint.io/"
    }

}