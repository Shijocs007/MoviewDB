package com.moviedb.movieapp.utils

import java.io.IOException

/**
 * This file is used for declaring custom exceptions
 */

/**
*ApiException is thrown when an error occurs with retrofit call in {@see SafeApiRequest}
 */
class ApiException(message : String) : IOException(message)

/**
 * NoInternetException is thrown when an error occurs with retrofit call in {@see NetworkConnectionIntercepter}
 **/
class NoInternetException(message: String) : IOException(message)