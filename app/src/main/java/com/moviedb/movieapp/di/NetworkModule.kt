package com.moviedb.movieapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.network.NetworkConnectionIntercepter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

    /**
     * provides Gson for retrofit
     *
     * @return Gson for retrofit converter factory
     * */
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    /**
     * provide network interceptor for connection debugging
     *
     * @param context applicationContext
     * @return
     * */
    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context) : NetworkConnectionIntercepter {
        return NetworkConnectionIntercepter(context)
    }

    /**
     * provide okhttp client for retrofit
     *
     * @param networkConnectionIntercepter http interceptor
     * @return OkHttpClient
     * */
    @Singleton
    @Provides
    fun provideOkhttp(networkConnectionIntercepter: NetworkConnectionIntercepter) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionIntercepter)
            .build()
    }

    /**
     * provide retrofit builder
     *
     * @param gson for json to object convert
     * @param okHttpClient
     * @return retrofit instance
     * */
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit.Builder): MovieApi {
        return retrofit
            .build()
            .create(MovieApi::class.java)
    }

}