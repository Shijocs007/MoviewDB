package com.moviedb.movieapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SharedPrefModule {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPreferences {

        return context.getSharedPreferences("MySharedPrefMovieDb", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPrefEditor(preferences: SharedPreferences) : SharedPreferences.Editor {

        return preferences.edit()
    }
}