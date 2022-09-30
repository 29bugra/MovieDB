package com.mobillium.movieDB.core.resources

import retrofit2.Response
import retrofit2.Retrofit

class HandleDataSource(private val retrofit: Retrofit) {
    suspend fun <T> getResponse(request: suspend () -> Response<T?>?, defaultErrorMessage: String): NetworkResult<T> {
        return try {
            val result = request.invoke()
            if (result?.isSuccessful == true) {
                return NetworkResult.Success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                NetworkResult.Failure(errorResponse)
            }
        } catch (e: Throwable) {
            NetworkResult.Failure(message = "Unknown Error")
        }
    }
}