package com.aungkhantphyo.movies.localdatabase.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.aungkhantphyo.movies.domain.Movie
import com.aungkhantphyo.movies.localdatabase.entity.RemoteKey
import com.aungkhantphyo.movies.localdatabase.entity.MovieEntity

@Dao
interface MovieDao {

    //For Upcoming
    @Transaction
    @Query("SELECT * FROM movies where type=:type")
    fun getUpcomingMovies(type:String):PagingSource<Int,MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUpcomingMovies( upcomingMovies: List<MovieEntity>)

    @Query("DELETE FROM movies where type=:type")
    suspend fun deleteUpcomingMovies(type: String)

    //For Popular
    @Transaction
    @Query("SELECT * FROM movies where type=:type")
    fun getPopularMovies(type:String):PagingSource<Int,MovieEntity>

    @Transaction
    @Query("SELECT * FROM movies where isFav=:favorite")
    fun getFavoriteMovies(favorite: Boolean = true ):PagingSource<Int,MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularMovies(popularMovies: List<MovieEntity>)

    @Query("DELETE FROM movies where type=:type")
    suspend fun delete(type: String)

    @Transaction
    @Query("select * from movies where movieId=:movieId")
    suspend fun getMovie(movieId:String):MovieEntity

    @Query("update movies set isFav = :favorite where  movieId = :movieId")
    fun toggleFavorite(movieId: String, favorite: Boolean)
}

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys where keyId=:id")
    suspend fun remoteKey(id:String): RemoteKey

    @Query("DELETE FROM remote_keys")
    suspend fun delete()
}