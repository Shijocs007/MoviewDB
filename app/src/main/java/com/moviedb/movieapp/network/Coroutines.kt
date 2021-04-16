package com.moviedb.movieapp.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Coroutine helper class for launching coroutines
 * */
object Coroutines {
    // this will execute in main thread
    fun main(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
}