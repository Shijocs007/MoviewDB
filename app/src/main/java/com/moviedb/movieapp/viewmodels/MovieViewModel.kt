package com.moviedb.movieapp.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.NetworkState
import com.moviedb.movieapp.repository.MovieRepository

class MovieViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val  moviePagedList : LiveData<PagedList<Movie>> by lazy {
        repository.fetchMovieList()
    }

    val  networkState : LiveData<NetworkState> by lazy {
        repository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        val test = moviePagedList.value
        return moviePagedList.value?.isNullOrEmpty() ?: true
    }
}