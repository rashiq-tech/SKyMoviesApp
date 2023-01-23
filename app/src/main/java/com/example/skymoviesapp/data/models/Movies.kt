package com.example.skymoviesapp.data.models

import com.google.gson.annotations.SerializedName
data class Movies(@SerializedName("DVD") val dvd :String,
                  @SerializedName("Plot") val plot :String,
                  @SerializedName("Type") val type :String,
                  @SerializedName("Year") val year :Int,
                  @SerializedName("Genre") val genre :String,
                  @SerializedName("Rated") val rated :String,
                  @SerializedName("Title") val title :String,
                  @SerializedName("Actors") val actors :String,
                  @SerializedName("Awards") val awards :String,
                  @SerializedName("Poster") val poster :String,
                  @SerializedName("Writer") val writer :String,
                  @SerializedName("imdbID") val imdbID :String,
                  @SerializedName("Country") val country :String,
                  @SerializedName("Ratings") val ratings :ArrayList<Rating>,
                  @SerializedName("Runtime") val runtime :String,
                  @SerializedName("Director") val director :String,
                  @SerializedName("Language") val language :String,
                  @SerializedName("Released") val released :String,
                  @SerializedName("Response") val response :String,
                  @SerializedName("BoxOffice") val boxOffice :String,
                  @SerializedName("Metascore") val metaScore :Int,
                  @SerializedName("imdbVotes") val imdbVotes :String,
                  @SerializedName("Production") val production :String,
                  @SerializedName("imdbRating") val imdbRating :Double) {

    data class Rating(@SerializedName("Value") val value :String, @SerializedName("Source") val source :String)

}