package com.mobillium.movieDB.core.network.services

import com.mobillium.movieDB.ui.details.model.MovieDetailsResponse
import com.mobillium.movieDB.ui.home.model.MovieBannerListResponse
import com.mobillium.movieDB.ui.home.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    @GET("/3/movie/now_playing")
    suspend fun getMovieBannerList(@Query("api_key") apiKey: String): Response<MovieBannerListResponse?>?

    @GET("/3/movie/upcoming")
    suspend fun getMovieList(@Query("api_key") apiKey: String, @Query("page") page: String): Response<MovieListResponse?>?

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse?>?
}