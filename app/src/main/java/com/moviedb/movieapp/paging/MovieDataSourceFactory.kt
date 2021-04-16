package com.moviedb.movieapp.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.paging.MovieDataSource
import com.moviedb.movieapp.repository.MovieRepository

class MovieDataSourceFactory(private val repository: MovieRepository) : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource =
            MovieDataSource(repository)
        moviesLiveDataSource.postValue(movieDataSource)

        return movieDataSource
    }
}