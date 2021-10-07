package com.aungkhantphyo.movies.localdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aungkhantphyo.movies.domain.PopularMovies


@Entity(tableName = "popularMovies")
data class PopularMoviesEntity(
    @PrimaryKey val movieId:String,
    val title:String,
    val posterUrl:String,
    val rating:Double
)

fun PopularMoviesEntity.asDomain():PopularMovies{
    return PopularMovies(
        id = movieId,
        title = title,
        posterUrl = posterUrl,
        rating = rating
    )
}