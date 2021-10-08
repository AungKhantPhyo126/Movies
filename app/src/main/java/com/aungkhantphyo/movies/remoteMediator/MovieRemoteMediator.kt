package com.aungkhantphyo.movies.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.aungkhantphyo.movies.di.API_KEY
import com.aungkhantphyo.movies.localdatabase.AppDatabase
import com.aungkhantphyo.movies.localdatabase.dao.RemoteKeyDao
import com.aungkhantphyo.movies.localdatabase.dao.MovieDao
import com.aungkhantphyo.movies.localdatabase.entity.*
import com.aungkhantphyo.movies.network.MovieApiService
import com.aungkhantphyo.movies.network.dto.asEntity
import com.aungkhantphyo.movies.network.responses.MovieResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator (
    private val type:String,
    private val movieDao: MovieDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val movieApiService: MovieApiService,
    private val database: AppDatabase
) : RemoteMediator<Int, MovieEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try{

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND->{
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKey(if (type== UPCOMING) UPCOMING_PAGE_KEY else POPULAR_PAGE_KEY)
                    }

                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    remoteKey.nextKey
                }
            }
            val keyId=if (type== UPCOMING) UPCOMING_PAGE_KEY else POPULAR_PAGE_KEY
            val response=if (type== UPCOMING){
                movieApiService.getUpcomingMovies(
                    apiKey = API_KEY, pageNumber = loadKey
                )
            }else{
                movieApiService.getPopularMovies(
                    apiKey = API_KEY,pageNumber = loadKey
                )
            }

            val isLastPage = loadKey == response.body()?.totalPages
            database.withTransaction {
                remoteKeyDao.insertOrReplace(
                    RemoteKey(keyId = keyId,nextKey = if(isLastPage) null else loadKey+1)
                )
                movieDao.saveUpcomingMovies(response.body()?.results?.map { it.asEntity(type) }.orEmpty())
            }

            MediatorResult.Success(
                endOfPaginationReached = loadKey == response.body()?.totalPages
            )
        }catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}