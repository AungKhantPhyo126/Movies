package com.aungkhantphyo.movies.localdatabase.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.aungkhantphyo.movies.domain.UpcomingMovies
import com.aungkhantphyo.movies.localdatabase.entity.PopularMoviesEntity
import com.aungkhantphyo.movies.localdatabase.entity.RemoteKey
import com.aungkhantphyo.movies.localdatabase.entity.UpcomingMoviesEntity

@Dao
interface UpcomingMoviesDao {
    @Transaction
    @Query("SELECT * FROM upcomingMovies")
    fun getUpcomingMovies():PagingSource<Int,UpcomingMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUpcomingMovies( upcomingMovies: List<UpcomingMoviesEntity>)

    @Query("DELETE FROM upcomingMovies")
    suspend fun delete()
}

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys")
    suspend fun remoteKey(): RemoteKey

    @Query("DELETE FROM remote_keys")
    suspend fun delete()
}