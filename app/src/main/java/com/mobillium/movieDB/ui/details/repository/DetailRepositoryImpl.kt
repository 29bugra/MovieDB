package com.mobillium.movieDB.ui.details.repository

import com.mobillium.movieDB.core.resources.HandleDataSource
import com.mobillium.movieDB.core.resources.NetworkResult
import com.mobillium.movieDB.core.network.services.MovieServices
import com.mobillium.movieDB.ui.details.model.MovieDetailsResponse

class DetailRepositoryImpl(private val service: MovieServices, private val handleDataSource: HandleDataSource) :
    DetailRepository {

    override suspend fun getMovieDetail(movieId: String, apiKey: String): NetworkResult<MovieDetailsResponse?> {
        return handleDataSource.getResponse(
            request = { service.getMovieDetail(movieId, apiKey) },
            defaultErrorMessage = "Error fetching Movie list"
        )
    }
}