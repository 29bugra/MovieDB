package com.mobillium.movieDB.ui.details.model

data class MovieDetailsResponse(
    val original_language: String? = null,
    val imdb_id: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val backdrop_path: String? = null,
    val revenue: Int? = null,
    val genres: List<GenresItem?>? = null,
    val popularity: Double? = null,
    val production_countries: List<ProductionCountriesItem?>? = null,
    val id: Int? = null,
    val vote_count: Double? = null,
    val budget: Int? = null,
    val overview: String? = null,
    val original_title: String? = null,
    val runtime: Int? = null,
    val poster_path: String? = null,
    val spoken_languages: List<SpokenLanguagesItem?>? = null,
    val production_companies: List<ProductionCompaniesItem?>? = null,
    val release_date: String? = null,
    val vote_average: Double? = null,
    val belongs_to_collection: Any? = null,
    val tagline: String? = null,
    val adult: Boolean? = null,
    val homepage: String? = null,
    val status: String? = null
)

data class SpokenLanguagesItem(
    val name: String? = null,
    val iso_639_1: String? = null,
    val english_name: String? = null
)

data class GenresItem(
    val name: String? = null,
    val id: Int? = null
)

data class ProductionCompaniesItem(
    val logoPath: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val originCountry: String? = null
)

data class ProductionCountriesItem(
    val iso_3166_1: String? = null,
    val name: String? = null
)

