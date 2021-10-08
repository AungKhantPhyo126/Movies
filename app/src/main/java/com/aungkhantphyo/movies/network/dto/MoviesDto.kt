package com.aungkhantphyo.movies.network.dto

import com.aungkhantphyo.movies.localdatabase.entity.MovieEntity
import com.aungkhantphyo.movies.localdatabase.entity.UPCOMING
import com.squareup.moshi.Json

data class MoviesDto(
    val id:String?,
    val title:String?,

    @Json(name = "poster_path")
    val posterPath:String?,
    val overview:String?
)

fun MoviesDto.asEntity(type:String):MovieEntity{
    return MovieEntity(
        id=type+id,
        movieId = id.orEmpty(),
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        type = type,
        isFav = false
    )
}
