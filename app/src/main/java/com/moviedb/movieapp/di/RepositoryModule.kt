package com.moviedb.movieapp.di

import com.moviedb.movieapp.repository.MovieRepository
import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    /**
     * provide movie repository class
     *
     * @param api MovieApi instance where we written all api requests
     * @param movieDatabase to get the data form local db
     * @return repository module
     * */
    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi, movieDatabase: MovieDatabase) : MovieRepository {
        return MovieRepository(
            api,
            movieDatabase
        );
    }
}