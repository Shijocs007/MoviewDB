package com.moviedb.movieapp.network

import com.moviedb.movieapp.network.model.MovieResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getMovies(@Query("page") page : Int ) : Response<MovieResult>
}