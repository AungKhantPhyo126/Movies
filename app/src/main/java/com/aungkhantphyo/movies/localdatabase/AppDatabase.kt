package com.aungkhantphyo.movies.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aungkhantphyo.movies.localdatabase.dao.PopularMoviesDao
import com.aungkhantphyo.movies.localdatabase.dao.RemoteKeyDao
import com.aungkhantphyo.movies.localdatabase.dao.UpcomingMoviesDao
import com.aungkhantphyo.movies.localdatabase.entity.PopularMoviesEntity
import com.aungkhantphyo.movies.localdatabase.entity.RemoteKey
import com.aungkhantphyo.movies.localdatabase.entity.UpcomingMoviesEntity

@Database(
    entities = [
        PopularMoviesEntity::class,
        UpcomingMoviesEntity::class,
        RemoteKey::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun remoteKeyDao():RemoteKeyDao
    abstract fun popularMoviesDao():PopularMoviesDao
    abstract fun upcomingMoviesDao():UpcomingMoviesDao
    companion object {
        fun create(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "AppDatabase"
            ).allowMainThreadQueries()
                .build()
        }
    }
}