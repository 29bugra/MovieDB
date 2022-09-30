package com.mobillium.movieDB.ui.details.repository

import com.mobillium.movieDB.core.resources.NetworkResult
import com.mobillium.movieDB.ui.details.model.MovieDetailsResponse

interface DetailRepository {
    suspend fun getMovieDetail(movieId: String, apiKey: String): NetworkResult<MovieDetailsResponse?>
}