package com.aungkhantphyo.movies.network.dto

import com.aungkhantphyo.movies.localdatabase.entity.PopularMoviesEntity
import com.squareup.moshi.Json

data class PopularMoviesDto(
    val id:String?,
    val title:String?,
    @field:Json(name = "poster_path")
    val posterUrl:String?,
    @field:Json(name = "vote_average")
    val rating:Double?
)

fun PopularMoviesDto.asEntity(): PopularMoviesEntity {
    return PopularMoviesEntity(
        movieId = id.orEmpty(),
        title = title.orEmpty(),
        posterUrl = posterUrl.orEmpty(),
        rating = rating?:0.0
    )
}