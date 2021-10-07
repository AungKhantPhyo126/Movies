package com.aungkhantphyo.movies.network.dto

import com.aungkhantphyo.movies.domain.UpcomingMovies
import com.aungkhantphyo.movies.localdatabase.entity.UpcomingMoviesEntity
import com.squareup.moshi.Json

data class UpcomingMoviesDto(
    val id:String?,
    val title:String?,

    @Json(name = "poster_path")
    val posterPath:String?
)

fun UpcomingMoviesDto.asEntity():UpcomingMoviesEntity{
    return UpcomingMoviesEntity(
        id= id.orEmpty(),
        title = title.orEmpty(),
        posterPath = posterPath.orEmpty()
    )
}
