package com.aungkhantphyo.movies.localdatabase.dao

import androidx.room.*
import com.aungkhantphyo.movies.domain.PopularMovies
import com.aungkhantphyo.movies.localdatabase.entity.PopularMoviesEntity

@Dao
interface PopularMoviesDao {
    @Transaction
    @Query("SELECT * FROM popularMovies")
    suspend fun getList(): List<PopularMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg popularMovies: PopularMoviesEntity)

    @Delete
    suspend fun delete(popularMovies: PopularMoviesEntity)
}