package com.mobillium.movieDB.ui.home.repository

import com.mobillium.movieDB.core.resources.HandleDataSource
import com.mobillium.movieDB.core.resources.NetworkResult
import com.mobillium.movieDB.core.network.services.MovieServices
import com.mobillium.movieDB.ui.home.model.MovieBannerListResponse
import com.mobillium.movieDB.ui.home.model.MovieListResponse

class HomeRepositoryImpl(private val service: MovieServices, private val handleDataSource: HandleDataSource) : HomeRepository {
    override suspend fun getMovieBannerList(apiKey: String): NetworkResult<MovieBannerListResponse?> {
        return handleDataSource.getResponse(
            request = { service.getMovieBannerList(apiKey) },
            defaultErrorMessage = "Error fetching Movie list"
        )
    }

    override suspend fun getMovieList(apiKey: String, page: String): NetworkResult<MovieListResponse?> {
        return handleDataSource.getResponse(
            request = { service.getMovieList(apiKey, page) },
            defaultErrorMessage = "Error fetching Movie list"
        )
    }
}