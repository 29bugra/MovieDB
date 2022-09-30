package com.mobillium.movieDB.core.resources


sealed class NetworkResult<out T> {
    data class Success<T>(val response: T?) : NetworkResult<T>()
    data class Failure<T>(val error: Error? = null, val message: String? = null) : NetworkResult<T>()
}