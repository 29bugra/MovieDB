package com.mobillium.movieDB.ui.home.repository

import com.mobillium.movieDB.core.resources.NetworkResult
import com.mobillium.movieDB.ui.home.model.MovieBannerListResponse
import com.mobillium.movieDB.ui.home.model.MovieListResponse

interface HomeRepository {
    suspend fun getMovieBannerList(apiKey: String): NetworkResult<MovieBannerListResponse?>

    suspend fun getMovieList(apiKey: String,page:String): NetworkResult<MovieListResponse?>
}