package com.aungkhantphyo.movies.localdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aungkhantphyo.movies.domain.UpcomingMovies


@Entity(tableName = "upcomingMovies")
data class UpcomingMoviesEntity(
    @PrimaryKey val id:String,
    val title:String,
    val posterPath:String
)

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey(autoGenerate = true) val nextKey: Int?
    )

fun UpcomingMoviesEntity.asDomain():UpcomingMovies{
    return UpcomingMovies(
        id = id,
        title = title,
        posterPath = posterPath
    )
}
