package com.mobillium.movieDB.ui.home.model

data class MovieBannerListResponse(
    val dates: Dates? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<ResultsItem>? = null,
    val totalResults: Int? = null
)

data class MovieListResponse(
    val dates: Dates? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<ResultsItem>? = null,
    val totalResults: Int? = null
)

data class Dates(
    val maximum: String? = null,
    val minimum: String? = null
)

data class ResultsItem(
    val overview: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val genreIds: List<Int?>? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val release_date: String? = null,
    val popularity: Double? = null,
    val vote_average: Double? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val vote_count: Int? = null
)

