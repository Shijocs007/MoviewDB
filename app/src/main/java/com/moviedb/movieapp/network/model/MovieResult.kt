package com.moviedb.movieapp.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.moviedb.movieapp.models.Movie

/**
 * this class is the response object of popular movie list retrofit call
 * */
data class MovieResult(
    @Expose @SerializedName("results") val movies : List<Movie>,
    @Expose @SerializedName("page") val page : Int,
    @Expose @SerializedName("total_results") val total_results : Int,
    @Expose @SerializedName("total_pages") val total_pages : Int
)