package com.moviedb.movieapp.network

import android.content.Context
import android.net.ConnectivityManager
import com.moviedb.movieapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionIntercepter(context : Context) : Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable()) {
            throw NoInternetException("Make sure network is active")
        }

        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", "6bf8ddb55a7ad70071bfa67e26407194").build()
        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }

    private fun isInternetAvailable() : Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}