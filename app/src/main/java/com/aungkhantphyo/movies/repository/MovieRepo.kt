package com.aungkhantphyo.movies.repository

import androidx.paging.*
import com.aungkhantphyo.movies.domain.UpcomingMovies
import com.aungkhantphyo.movies.localdatabase.AppDatabase
import com.aungkhantphyo.movies.localdatabase.dao.RemoteKeyDao
import com.aungkhantphyo.movies.localdatabase.dao.UpcomingMoviesDao
import com.aungkhantphyo.movies.localdatabase.entity.UpcomingMoviesEntity
import com.aungkhantphyo.movies.localdatabase.entity.asDomain
import com.aungkhantphyo.movies.network.MovieApiService
import com.aungkhantphyo.movies.remoteMediator.UpcomingMoviesRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepo @Inject constructor(
    private val movieApiService: MovieApiService,
    private val upcomingMoviesDao: UpcomingMoviesDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val appDatabase: AppDatabase,

) {

    @OptIn(ExperimentalPagingApi::class)
    fun fetchUpcomingMovies() :Flow<PagingData<UpcomingMovies>>{
        return Pager(
            config = PagingConfig(10),
            remoteMediator = UpcomingMoviesRemoteMediator(upcomingMoviesDao,remoteKeyDao,movieApiService,appDatabase)
        ) {
            upcomingMoviesDao.getUpcomingMovies()
        }.flow.map { pagingData->
            pagingData.map { it.asDomain() }
        }
    }

//    fun getUpcomingMovies(forceRefresh: Boolean): Flow<PagingData<UpcomingMovies>> {
//        if (forceRefresh){
//            return fetchUpcomingMovies().map { pagingData->
//                pagingData.map{
//                    it.asDomain()
//                }
//            }
//        }
//        return Pager(
//            PagingConfig(pageSize = 10)
//        ) {
//            upcomingMoviesDao.getUpcomingMovies()
//        }.flow.map { pagingData ->
//            pagingData.map {
//                it.asDomain()
//            }
//        }
//    }
}