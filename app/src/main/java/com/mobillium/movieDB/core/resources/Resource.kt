package com.mobillium.movieDB.core.resources

sealed class Resource<out T> {
    data class Success<T>(val response: T?) : Resource<T>()
    data class Failure<T>(val error: Error? = null, val message: String? = null) : Resource<T>()
    object Loading : Resource<Nothing>()
}