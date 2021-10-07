package com.aungkhantphyo.movies.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.aungkhantphyo.movies.di.API_KEY
import com.aungkhantphyo.movies.localdatabase.AppDatabase
import com.aungkhantphyo.movies.localdatabase.dao.RemoteKeyDao
import com.aungkhantphyo.movies.localdatabase.dao.UpcomingMoviesDao
import com.aungkhantphyo.movies.localdatabase.entity.RemoteKey
import com.aungkhantphyo.movies.localdatabase.entity.UpcomingMoviesEntity
import com.aungkhantphyo.movies.network.MovieApiService
import com.aungkhantphyo.movies.network.dto.asEntity
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UpcomingMoviesRemoteMediator (
    private val upcomingMoviesDao: UpcomingMoviesDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val movieApiService: MovieApiService,
    private val database: AppDatabase
) : RemoteMediator<Int, UpcomingMoviesEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UpcomingMoviesEntity>
    ): MediatorResult {
        return try{

            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND->{
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKey()
                    }

                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    remoteKey.nextKey
                }
            }

            val response = movieApiService.getUpcomingMovies(
                apiKey = API_KEY, pageNumber = loadKey?:1
            )
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    upcomingMoviesDao.delete()
                    remoteKeyDao.delete()
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(response.body()?.page?:1 +1)
                )

                upcomingMoviesDao.saveUpcomingMovies(response.body()?.results?.map { it.asEntity() }.orEmpty())
            }
            MediatorResult.Success(
                endOfPaginationReached = response.body()?.page == response.body()?.totalPages
            )
        }catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}