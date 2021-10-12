package com.aungkhantphyo.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.aungkhantphyo.movies.domain.Movie
import com.aungkhantphyo.movies.localdatabase.AppDatabase
import com.aungkhantphyo.movies.localdatabase.dao.RemoteKeyDao
import com.aungkhantphyo.movies.localdatabase.dao.MovieDao
import com.aungkhantphyo.movies.localdatabase.entity.POPULAR
import com.aungkhantphyo.movies.localdatabase.entity.UPCOMING
import com.aungkhantphyo.movies.localdatabase.entity.asDomain
import com.aungkhantphyo.movies.network.MovieApiService
import com.aungkhantphyo.movies.remoteMediator.MovieRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepo @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val appDatabase: AppDatabase,

    ) {

    @OptIn(ExperimentalPagingApi::class)
    fun fetchUpcomingMovies() :Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(10),
            remoteMediator = MovieRemoteMediator(UPCOMING,movieDao,remoteKeyDao,movieApiService,appDatabase)
        ) {
            movieDao.getUpcomingMovies(UPCOMING)
        }.flow.map { pagingData->
            pagingData.map { it.asDomain() }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPopularMovies() :Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(10),
            remoteMediator = MovieRemoteMediator(POPULAR,movieDao,remoteKeyDao,movieApiService,appDatabase)
        ) {
            movieDao.getPopularMovies(POPULAR)
        }.flow.map { pagingData->
            pagingData.map { it.asDomain() }
        }
    }

    fun fetchFavoriteMovies():Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(10)
        ){
            movieDao.getFavoriteMovies(true)
        }.flow.map { pagingData->
            pagingData.map { it.asDomain() }
        }
    }

    suspend fun getMovieDetail(movieId:String):Movie{
        return movieDao.getMovie(movieId).asDomain()
    }

    suspend fun toggleFavorite(movieId:String){
        val movie = movieDao.getMovie(movieId).asDomain()
        if (!movie.isFav){
            movieDao.toggleFavorite(movieId,true)
        }else movieDao.toggleFavorite(movieId,false)

    }
}